package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.cache.SystemFieldsRetriever;
import com.cjhxfund.autocode.model.BasicType;
import com.cjhxfund.autocode.model.converter.DBTypeConverter;
import com.cjhxfund.autocode.model.out.table.DBTable;
import com.cjhxfund.autocode.model.out.table.FileType;
import com.cjhxfund.autocode.model.out.table.TableField;
import com.cjhxfund.autocode.model.out.table.TableIndex;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.StdtypeType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.system.SubsystemType;
import com.cjhxfund.autocode.wesklake.model.xsd.table.IndexsType;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableFieldType;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableIndexType;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Component
public class DBTableAssembler implements ContentFileAssembler<DBTable, TableType> {

    private final static String PK_INDEX_NAME = "0";
    private final static String TEMP_TABLE_NAME_PREFIX = "z";
    private final static String CHECK_TABLE_NAME_PREFIX = "tcheck";
    private final static String TEMP_CHECK_TABLE_NAME_PREFIX = "zcheck";
    private final static String TABLE_DEFAULT_CHECK_MODE = "数据库复核";
    private final static String MDB_DEFAULT_CHECK_MODE = "MDB复核";

    @Autowired
    private SystemFieldsRetriever fieldsRetriever;

    @Override
    public DBTable assemble(String subSystem, String moduleName, String tableFileName, TableType tableType) {
        DBTable dbTable = DBTable
                .builder()
                .chineseName(tableFileName)
                .name(tableType.getEnname())
                .shadowName(TEMP_TABLE_NAME_PREFIX + tableType.getEnname().substring(1))
                .subSystem(subSystem)
                .tableFileName(tableFileName)
                .fileType(FileType.DB_SQL_FILE)
                .build();
        dbTable.setFields(assembleField(tableType));
        dbTable.setLastField(assembleLastField(tableType));
        dbTable.setPkIndex(assemblePKIndex(tableType));
        dbTable.setIndexes(assembleIndex(tableType));
        dbTable.setAllIndexes(assembleAllIndex(tableType));
        dbTable.setTableModuleName(assembleModule(subSystem, moduleName));

        if (tableType.getHasHistoryTable().equalsIgnoreCase("true")) {
            dbTable.setHasHisTable(true);
        }

        if (tableType.getCheckMode().equalsIgnoreCase(MDB_DEFAULT_CHECK_MODE)) {
            dbTable.setNeedMDBCheck(true);
        }
        if (tableType.getCheckMode().equalsIgnoreCase(TABLE_DEFAULT_CHECK_MODE)) {
            dbTable.setNeedDBCheck(true);
            String tableName = dbTable.getName().substring(1);
            dbTable.setCheckName(CHECK_TABLE_NAME_PREFIX + tableName);
            dbTable.setCheckShadowName(TEMP_CHECK_TABLE_NAME_PREFIX + tableName);
        }
        return dbTable;
    }

    public DBTable assembleSimpleTable(String subSystem, String moduleName, TableType tableType) {
        DBTable dbTable = DBTable
                .builder()
                .name(tableType.getEnname())
                .shadowName(TEMP_TABLE_NAME_PREFIX + tableType.getEnname().substring(1))
                .subSystem(subSystem)
                .tableFileName("")
                .fileType(FileType.MDB_CXX_FILE)
                .build();
        dbTable.setFields(assembleField(tableType));
        dbTable.setLastField(assembleLastField(tableType));
        dbTable.setAllIndexes(assembleAllIndex(tableType));
        dbTable.setTableModuleName(assembleModule(subSystem, moduleName));
        return dbTable;
    }

    /*********************** assemble database script file ***********************/
    private String assembleModule(String subSystem, String moduleName) {
        SubsystemType subsystemType = fieldsRetriever.findSubSystem(subSystem);
        if (subsystemType == null) {
            throw new RuntimeException("Subsystem is not defined");
        }
        String subSystemEnName = subsystemType.getEnName();
        return subSystemEnName + File.separator + moduleName;
    }

    private TableField[] assembleField(TableType tableType) {
        List<TableFieldType> fieldTypes = tableType.getFields().getTableField();
        TableField[] fields = new TableField[fieldTypes.size() - 1];
        for (int i = 0; i < fieldTypes.size() - 1; i++) {
            TableFieldType _field = fieldTypes.get(i);
            TableField sampleField = TableField.processor.apply(_field);
            setFieldTypeAttributes(sampleField);
            fields[i] = sampleField;
        }
        return fields;
    }

    private void setFieldTypeAttributes(TableField tableField) {
        StdtypeType stdtypeType = fieldsRetriever.findField(tableField.getName());
        tableField.setLength(stdtypeType.getLength());
        tableField.setOrigType(BasicType.valueOf(stdtypeType.getBasicType()));
        // convert to database type using custom defined
        String dbType = DBTypeConverter.convert(BasicType.valueOf(stdtypeType.getBasicType()));
        tableField.setType(dbType);
        tableField.defaultValue(dbType, stdtypeType.getPrecision(), tableField.getNullable());
    }

    private TableField assembleLastField(TableType tableType) {
        List<TableFieldType> fieldTypes = tableType.getFields().getTableField();
        TableFieldType _lastFieldType = fieldTypes.get(fieldTypes.size() - 1);
        TableField sampleField =  TableField.processor.apply(_lastFieldType);
        setFieldTypeAttributes(sampleField);
        return sampleField;
    }

    private TableIndex[] assembleIndex(TableType tableType) {
        List<TableIndexType> indexTypes = tableType.getIndexs().getTableIndex();
        TableIndex[] indexes = new TableIndex[findAllDBIndex(tableType).size()];
        for (int i = 0; i < indexTypes.size(); i++) {
            TableIndexType indexType = indexTypes.get(i);
            // JUST PROCESS "DATABASE" CATEGORY WHEN APPEND INDEX
            if (!indexType.getUseway().equals("MDB")) {
                indexes[i] = TableIndex.processor.apply(indexType);
            }
        }
        return indexes;
    }

    private TableIndex[] assembleAllIndex(TableType tableType) {
        List<TableIndexType> indexTypes = tableType.getIndexs().getTableIndex();
        TableIndex[] indexes = new TableIndex[indexTypes.size()];
        for (int i = 0; i < indexTypes.size(); i++) {
            TableIndexType indexType = indexTypes.get(i);
            // JUST PROCESS "DATABASE" CATEGORY WHEN APPEND INDEX
            indexes[i] = TableIndex.processor.apply(indexType);
        }
        return indexes;
    }

    private TableIndex assemblePKIndex(TableType tableType) {
        return TableIndex.processor.apply(findPKIndex(tableType.getIndexs()));
    }

    private List<TableIndex> findAllDBIndex(TableType tableType) {
        List<TableIndexType> indexTypes = tableType.getIndexs().getTableIndex();
        List<TableIndex> tableIndexes = new ArrayList<>();
        for (TableIndexType indexType : indexTypes) {
            if (!indexType.getUseway().equalsIgnoreCase("MDB")) {
                tableIndexes.add(TableIndex.processor.apply(indexType));
            }
        }
        return tableIndexes;
    }

    private static TableIndexType findPKIndex(IndexsType indexsType) {
        TableIndexType tableIndexType = null;
        for (TableIndexType indexType : indexsType.getTableIndex()) {
            if (indexType.getName().equals(PK_INDEX_NAME)) {
                tableIndexType = indexType;
            }
        }
        return tableIndexType;
    }

}
