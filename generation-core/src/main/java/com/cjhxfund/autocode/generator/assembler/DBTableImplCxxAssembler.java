package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.cache.SystemFieldsRetriever;
import com.cjhxfund.autocode.generator.builder.TableIndexBuilder;
import com.cjhxfund.autocode.model.BasicType;
import com.cjhxfund.autocode.model.DBIndexTypeConst;
import com.cjhxfund.autocode.model.out.mdb.DBTableImplCxx;
import com.cjhxfund.autocode.model.out.table.DBTable;
import com.cjhxfund.autocode.model.out.table.FileType;
import com.cjhxfund.autocode.model.out.table.TableField;
import com.cjhxfund.autocode.model.out.table.TableIndex;
import com.cjhxfund.autocode.scanner.DBTableModuleDefinition;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.StdtypeType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.system.SubsystemType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class DBTableImplCxxAssembler implements ContentFileAssembler<DBTableImplCxx, DBTable> {

    private final static String CXX_FILE_HASH_KEY = "#";
    private final static String PK_INDEX_NAME = "0";
    private final static String INDEX_DELTA_HASH_FUNC_CODE = "0x9e3779b9";

    @Autowired
    private DBTableModuleDefinition tableModuleDefinition;

    @Autowired
    private SystemFieldsRetriever fieldsRetriever;

    @Autowired
    private TableIndexBuilder indexBuilder;

    public DBTableImplCxx assembleCxxImplFile(DBTable dbTable) {
        DBTableImplCxx dbTableCxx = DBTableImplCxx
                .builder()
                .hashKey(CXX_FILE_HASH_KEY)
                .name(dbTable.getName())
                .subSystem(dbTable.getSubSystem())
                .fileType(FileType.MT_HXX_FILE)
                .build();
        SubsystemType subsystemType = fieldsRetriever.findSubSystem(dbTable.getSubSystem());
        dbTableCxx.setTableId(Integer.parseInt(tableModuleDefinition.findTableIDByName(dbTable.getName())));
        dbTableCxx.setSubSystem(subsystemType.getEnName());
        dbTableCxx.setUpperTableName(dbTable.getName().toUpperCase());
        dbTableCxx.setTableModuleName(subsystemType.getEnName());
        dbTableCxx.setUpperTableModuleName(subsystemType.getEnName().toUpperCase());
        dbTableCxx.setCreateDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

        // BUILD COMPARE KEY BY PK INDEX FIELDS
        dbTableCxx.setPkIndexCmp(assemblePKIndexCmpFields(dbTable));

        // BUILD REMOVE KEY ARGS AND RECORDS
        String indexKeyArgs = buildRemoveKeyArgs(dbTable.getPkIndex());
        dbTableCxx.setPkIndexArgs(indexKeyArgs);
        dbTableCxx.setPkIndexCorrectArgs(indexKeyArgs.substring(0, indexKeyArgs.length() - 2));
        dbTableCxx.setPkIndexRecords(buildRemoveKeyRecords(dbTable.getPkIndex()));

        // BUILD INIT INDEXES
        dbTableCxx.setIndexes(assembleTableIndexes(dbTable));
        dbTableCxx.setQueryIndexes(assembleQueryIndexes(dbTable));

        dbTableCxx.setDiffRecords(assembleDiffRecords(dbTable));
        // BUILD FIX FIELDS
        dbTableCxx.setAutoNumFills(assembleAutoFillRecords(dbTable, BasicType.DOUBLE));
        dbTableCxx.setAutoStrFills(assembleAutoFillRecords(dbTable, BasicType.STRING));

        // BUILD HASH_FUNC AND CMP_FUNC
        dbTableCxx.setPkHashFuncs(assemblePKIndexHashFuncs(dbTable));
        dbTableCxx.setHashFuncs(assembleIndexHashFuncs(dbTable));
        dbTableCxx.setCmpFuncs(assembleIndexCmpFuncs(dbTable));

        return dbTableCxx;
    }

    private String assemblePKIndexCmpFields(DBTable dbTable) {
        TableIndex[] indexes = dbTable.getIndexes();
        String cmpIndexField = "";
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i].getIndexName().equalsIgnoreCase(PK_INDEX_NAME)) {
                cmpIndexField = buildOneCmpPair(indexes[i]);
            }
        }
        return cmpIndexField;
    }

    private String buildOneCmpPair(TableIndex tIndex) {
        String fields = tIndex.getFieldName();
        String[] fieldArray = fields.split(",");
        StringBuilder pkIndexBuilder = new StringBuilder();
        for (String field : fieldArray) {
            StdtypeType stdType = fieldsRetriever.findField(field.trim());
            if (!stdType.getBasicType().equals(BasicType.STRING.name())) {
                pkIndexBuilder.append("(")
                        .append("a->").append(field.trim())
                        .append(" < ")
                        .append("b->").append(field.trim())
                        .append(") && ");
            } else {
                pkIndexBuilder
                        .append("strcmp(a->").append(field.trim())
                        .append(", ")
                        .append("b->").append(field.trim())
                        .append(") == 0 && ");
            }
        }
        return pkIndexBuilder.toString();
    }

    private String buildRemoveKeyArgs(TableIndex tIndex) {
        if (tIndex.getIndexName().equals("0")) {
            return indexBuilder.buildRemoveKey(tIndex);
        }
        return "";
    }

    private String[] buildRemoveKeyRecords(TableIndex tIndex) {
        String fields = tIndex.getFieldName();
        String[] fieldArray = fields.split(",");
        List<String> records = new ArrayList<>();
        for (String field : fieldArray) {
            StringBuilder recordBuilder = new StringBuilder();
            StdtypeType stdType = fieldsRetriever.findField(field.trim());
            if (stdType.getBasicType().equals(BasicType.STRING.name())) {
                recordBuilder
                        .append("strncpy(")
                        .append("record.").append(field.trim()).append(", ").append(field.trim()).append(", ")
                        .append(Integer.parseInt(stdType.getLength()) + 1).append(")").append(field.trim()).append("; \n")
                        .append("if (record.").append(field.trim()).append("[").append(stdType.getLength()).append("]")
                        .append(" != '\\0')")
                        .append(" record.").append(field.trim()).append("[").append(stdType.getLength()).append("]")
                        .append(" = '\\0')").append(";");

            } else {
                recordBuilder
                        .append("record.")
                        .append(field.trim())
                        .append(" = ")
                        .append(field.trim())
                        .append(";");
            }
            records.add(recordBuilder.toString());
        }
        return records.toArray(new String[records.size()]);
    }

    private DBTableImplCxx.TableIndex[] assembleTableIndexes(DBTable dbTable) {
        List<DBTableImplCxx.TableIndex> tableIndices = new ArrayList<>();
        TableIndex[] indexes = dbTable.getAllIndexes();
        for (int i = 0; i < indexes.length; i++) {
            DBTableImplCxx.TableIndex tableIndex = new DBTableImplCxx.TableIndex();
            tableIndex.setTableName(dbTable.getName());
            tableIndex.setIspk(indexes[i].getIndexType().equalsIgnoreCase(DBIndexTypeConst.PK_INDEX_TYPE_CN_NAME)
                    || indexes[i].getIndexType().equalsIgnoreCase(DBIndexTypeConst.UNIQUE_INDEX_TYPE_CN_NAME));
            tableIndex.setName(indexes[i].getFieldName());
            tableIndex.setNo(indexes[i].getNo());
            tableIndices.add(tableIndex);
        }
        return tableIndices.toArray(new DBTableImplCxx.TableIndex[tableIndices.size()]);
    }

    private String[] assembleDiffRecords(DBTable dbTable) {
        List<String> diffRecords = new ArrayList<>();
        TableField[] unsortedTableFields = dbTable.getFields();
        List<TableField> tableFields = Arrays.stream(unsortedTableFields).collect(Collectors.toList());
        Collections.sort(tableFields);
        for (TableField field : tableFields) {
            StringBuilder builder = new StringBuilder();
            if (field.getOrigType() == BasicType.DOUBLE) {
                builder.append("!DOUBLE_EQ(p1->").append(field.getName())
                        .append(", p2->").append(field.getName()).append(")");
            } else if (field.getOrigType() == BasicType.STRING) {
                builder.append("strncmp(p1->").append(field.getName())
                        .append(", p2->").append(field.getName())
                        .append(", ").append(Integer.parseInt(field.getLength()) + 1).append(")");
            } else {
                builder.append("p1->").append(field.getName())
                        .append(" != p2->").append(field.getName());
            }
            builder.append(" || ");
            diffRecords.add(builder.toString());
        }
        String lastField = diffRecords.get(diffRecords.size() - 1);
        String newField = lastField.substring(0, lastField.length() - 4);
        diffRecords.remove(diffRecords.size() - 1);
        diffRecords.add(newField);
        return diffRecords.toArray(new String[diffRecords.size()]);
    }

    private TableField[] assembleAutoFillRecords(DBTable dbTable, BasicType basicType) {
        List<TableField> fields = new ArrayList<>();
        TableField[] unsortedTableFields = dbTable.getFields();
        List<TableField> tableFields = Arrays.stream(unsortedTableFields).collect(Collectors.toList());
        Collections.sort(tableFields);
        for (TableField field : tableFields) {
            if (field.getOrigType() == BasicType.DOUBLE && basicType == BasicType.DOUBLE) {
                fields.add(TableField
                        .builder()
                        .name(field.getName())
                        .length(field.getLength())
                        .precision(field.getPrecision())
                        .build());
            }
            if (field.getOrigType() == BasicType.STRING && basicType == BasicType.STRING) {
                fields.add(TableField
                        .builder()
                        .name(field.getName())
                        .build());
            }
        }
        return fields.toArray(new TableField[fields.size()]);
    }

    private DBTableImplCxx.TableIndex[] assembleQueryIndexes(DBTable dbTable) {
        List<DBTableImplCxx.TableIndex> tableIndices = new ArrayList<>();
        TableIndex[] indexes = dbTable.getAllIndexes();
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i].getIndexType().equals(DBIndexTypeConst.PK_INDEX_TYPE_CN_NAME)) {
                continue;
            }
            DBTableImplCxx.TableIndex tableIndex = new DBTableImplCxx.TableIndex();
            tableIndex.setTableName(dbTable.getName());
            tableIndex.setName(indexes[i].getFieldName());
            tableIndex.setNo(indexes[i].getNo());
            String indexKeyArgs = indexBuilder.buildRemoveKey(indexes[i]);
            tableIndex.setFieldArgs(indexKeyArgs.substring(0, indexKeyArgs.length() - 2));
            tableIndex.setRecords(buildRecords(indexes[i]));
            tableIndex.setFieldExpression(buildOneCmpPair(indexes[i]));
            tableIndices.add(tableIndex);
        }
        return tableIndices.toArray(new DBTableImplCxx.TableIndex[tableIndices.size()]);
    }

    private String[] buildRecords(TableIndex tableIndex) {
        List<String> records = new ArrayList<>();
        String[] fields = tableIndex.getFieldName().split(",");
        for (int i = 0; i < fields.length; i++) {
            records.add("record." + fields[i].trim() + " = " + fields[i].trim() );
        }
        return records.toArray(new String[records.size()]);
    }

    private DBTableImplCxx.TableIndex assemblePKIndexHashFuncs(DBTable dbTable) {
        List<DBTableImplCxx.TableIndex> tableIndexes = new ArrayList<>();
        TableIndex[] indexes = dbTable.getIndexes();
        for (int i = 0; i < indexes.length; i++) {
            DBTableImplCxx.TableIndex tableIndex = new DBTableImplCxx.TableIndex();
            tableIndex.setTableName(dbTable.getName());
            tableIndex.setName(indexes[i].getFieldName());
            tableIndex.setNo(indexes[i].getNo());
            tableIndex.setRecords(buildHashFunc(indexes[i]));
            tableIndexes.add(tableIndex);
        }
        return tableIndexes.get(0);
    }

    private DBTableImplCxx.TableIndex[] assembleIndexHashFuncs(DBTable dbTable) {
        List<DBTableImplCxx.TableIndex> tableIndexes = new ArrayList<>();
        TableIndex[] indexes = dbTable.getAllIndexes();
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i].getIndexType().equals(DBIndexTypeConst.PK_INDEX_TYPE_CN_NAME)) {
                continue;
            }
            DBTableImplCxx.TableIndex tableIndex = new DBTableImplCxx.TableIndex();
            tableIndex.setTableName(dbTable.getName());
            tableIndex.setName(indexes[i].getFieldName());
            tableIndex.setNo(indexes[i].getNo());
            tableIndex.setRecords(buildHashFunc(indexes[i]));
            tableIndexes.add(tableIndex);
        }
        return tableIndexes.toArray(new DBTableImplCxx.TableIndex[tableIndexes.size()]);
    }

    private String[] buildHashFunc(TableIndex tIndex) {
        List<String> records = new ArrayList<>();
        String fields = tIndex.getFieldName();
        String[] fieldArray = fields.split(",");
        for (String field : fieldArray) {
            StringBuilder recordBuilder = new StringBuilder();
            StdtypeType stdType = fieldsRetriever.findField(field.trim());
            if (stdType.getBasicType().equals(BasicType.STRING.name())) {
                recordBuilder
                        .append("seed ^= rxmdb_hash(p->").append(field.trim()).append(")")
                        .append(" + ").append(INDEX_DELTA_HASH_FUNC_CODE) .append(" + ")
                        .append("(seed << 6) + (seed >> 2)")
                        .append(");");
            } else {
                recordBuilder
                        .append("boost::hash_combine(seed , p->")
                        .append(field.trim())
                        .append(");");
            }
            records.add(recordBuilder.toString());
        }
        return records.toArray(new String[records.size()]);
    }

    private DBTableImplCxx.IndexCmpFunc[] assembleIndexCmpFuncs(DBTable dbTable) {
        List<DBTableImplCxx.IndexCmpFunc> result = new ArrayList<>();
        for (TableIndex index : dbTable.getAllIndexes()) {
            if (index.getIndexType().equals(DBIndexTypeConst.PK_INDEX_TYPE_CN_NAME)) {
                continue;
            }
            DBTableImplCxx.IndexCmpFunc cmpFunc = new DBTableImplCxx.IndexCmpFunc();
            cmpFunc.setNo(index.getNo());
            cmpFunc.setFieldExpression(buildOneCmpPair(index));
            result.add(cmpFunc);
        }
        return result.toArray(new DBTableImplCxx.IndexCmpFunc[result.size()]);
    }

    @Override
    public DBTableImplCxx assemble(String subSystem, String moduleName, String destFileName, DBTable tableType) {
        log.warn("No implement");
        return null;
    }
}
