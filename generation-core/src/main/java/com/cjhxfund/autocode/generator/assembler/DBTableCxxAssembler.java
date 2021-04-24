package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.cache.SystemFieldsRetriever;
import com.cjhxfund.autocode.generator.builder.DBTableFieldBuilder;
import com.cjhxfund.autocode.generator.builder.MDBTableSeqBuilder;
import com.cjhxfund.autocode.generator.builder.TableIndexBuilder;
import com.cjhxfund.autocode.model.BasicType;
import com.cjhxfund.autocode.model.Pair;
import com.cjhxfund.autocode.model.converter.BoostTypeConverter;
import com.cjhxfund.autocode.model.out.mdb.*;
import com.cjhxfund.autocode.model.out.table.DBTable;
import com.cjhxfund.autocode.model.out.table.FileType;
import com.cjhxfund.autocode.model.out.table.TableField;
import com.cjhxfund.autocode.model.out.table.TableIndex;
import com.cjhxfund.autocode.scanner.DBTableModuleDefinition;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.StdtypeType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.system.SubsystemType;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class DBTableCxxAssembler implements ContentFileAssembler<DBTableCxx, DBTable> {

    private final static String CXX_FILE_HASH_KEY = "#";
    private final static String PK_INDEX_NAME = "0";
    private final static String RTTI_REGISTER_TABLE_FLAG = "table";
    private final static String MDB_DEFAULT_CHECK_MODE = "MDB复核";

    @Autowired
    private DBTableAssembler dbTableAssembler;

    @Autowired
    private DBTableModuleDefinition tableModuleDefinition;

    @Autowired
    private SystemFieldsRetriever fieldsRetriever;

    @Autowired
    private MDBTableSeqBuilder sequenceBuilder;

    @Autowired
    private TableIndexBuilder tableIndexBuilder;

    public DBTableCxx assembleCxxFile(DBTable dbTable) {
        DBTableCxx dbTableCxx = DBTableCxx
                .builder()
                .hashKey(CXX_FILE_HASH_KEY)
                .name(dbTable.getName())
                .subSystem(dbTable.getSubSystem())
                .fileType(FileType.MT_HXX_FILE)
                .build();
        SubsystemType subsystemType = fieldsRetriever.findSubSystem(dbTable.getSubSystem());
        dbTableCxx.setSubSystem(subsystemType.getEnName());
        dbTableCxx.setUpperTableName(dbTable.getName().toUpperCase());
        dbTableCxx.setTableModuleName(subsystemType.getEnName());
        dbTableCxx.setUpperTableModuleName(subsystemType.getEnName().toUpperCase());
        dbTableCxx.setIndexOrders(assembleCommonIndexOrder(dbTable));
        dbTableCxx.setPkIndexParam(tableIndexBuilder.buildIndex(dbTable.getPkIndex()));
        dbTableCxx.setTableIndexes(assembleTableIndexes(dbTable));
        dbTableCxx.setTableFields(assembleTableFields(dbTable));
        dbTableCxx.setEnums(assembleTableModuleEnums(dbTable));
        dbTableCxx.setLastEnum(assembleTableModuleLastEnum(dbTable));
        dbTableCxx.setSeqs(assembleSequenceInfos());
        dbTableCxx.setTables(assembleSubTables(dbTable));
        dbTableCxx.setCreateDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return dbTableCxx;
    }

    /*********************** assemble mt_module hxx file ***********************/
    private MDBSequence[] assembleSequences() {
        List<MDBSequence> dbSequences = sequenceBuilder.buildMDBSequence();
        return dbSequences.toArray(new MDBSequence[dbSequences.size()]);
    }

    private MDBSequenceInfo[] assembleSequenceInfos() {
        List<MDBSequenceInfo> dbSequences = sequenceBuilder.buildMDBSequenceFullInfo();
        return dbSequences.toArray(new MDBSequenceInfo[dbSequences.size()]);
    }

    private MDBSubTable[] assembleSubTables(DBTable dbTable) {
        List<MDBSubTable> subTables = new ArrayList<>();
        List<String> existingTables = tableModuleDefinition.findTablesOfModule(dbTable.getSubSystem());
        Collections.sort(existingTables);
        for (String name : existingTables) {
            String tableID = tableModuleDefinition.findTableIDByName(name);
            if (tableID == null)
                continue;
            TableType tableType = tableModuleDefinition.findTableTypeByName(name);
            boolean needCheck = tableType.getCheckMode().equalsIgnoreCase(MDB_DEFAULT_CHECK_MODE) ? true : false;
            DBTable dbTableTmp = dbTableAssembler.assembleSimpleTable(dbTable.getSubSystem(), dbTable.getTableModuleName(), tableType);
            subTables.add(buildOneSubTable(name, dbTableTmp, tableID, needCheck));
        }
        // append the new table
        String tableID = tableModuleDefinition.findTableIDByName(dbTable.getName());
        if (tableID != null) {
            MDBSubTable newSubTable = buildOneSubTable(dbTable.getName(), dbTable, tableID, false);
            subTables.add(newSubTable);
            if (dbTable.isNeedMDBCheck()) {
                subTables.add(buildOneSubTable(dbTable.getName(), dbTable, tableID, true));
            }
        }
        Collections.sort(subTables);
        return subTables.toArray(new MDBSubTable[subTables.size()]);
    }

    private MDBSubTable buildOneSubTable(String name, DBTable dbTable, String tableID, boolean needCheck) {
        MDBSubTable subTable = MDBSubTable
                .builder()
                .id(Integer.parseInt(tableID))
                .name(name)
                .size(0)
                .tableFlag(RTTI_REGISTER_TABLE_FLAG)
                .flag1("true")
                .flag2("false")
                .flag3("false")
                .build();

        if (needCheck) {
            subTable.setId(Integer.parseInt("1000" + tableID));
            subTable.setName("tcheck" + name.substring(1));
            List<TableField> tableFields = Stream.of(dbTable.getFields()).collect(Collectors.toList());
            TableField checkField = TableField.builder()
                    .name("check_id")
                    .type("INT32")
                    .length("10")
                    .origType(BasicType.INT32)
                    .precision("0")
                    .build();
            tableFields.add(checkField);
            dbTable.setFields(tableFields.toArray(new TableField[tableFields.size()]));
        }

        // sort table fields and build register fields
        DBTableFieldBuilder dbTableFieldBuilder = new DBTableFieldBuilder();
        dbTableFieldBuilder.sortFields(dbTable);
        List<MDBTableField> tableFields = dbTableFieldBuilder.buildFields(tableID, needCheck);
        subTable.setFields(tableFields.toArray(new MDBTableField[tableFields.size()]));

        // build register indexes
        List<DBRegisterIndex> indexList = tableIndexBuilder.buildRegisterIndex(dbTable, tableID, needCheck);
        subTable.setIndexes(indexList.toArray(new DBRegisterIndex[indexList.size()]));
        subTable.setSize(dbTableFieldBuilder.totalSize());
        return subTable;
    }

    private MDBTableEnum[] assembleTableModuleEnums(DBTable dbTable) {
        List<MDBTableEnum> enums = new ArrayList<>();
        List<String> existingTables = tableModuleDefinition.findTablesOfModule(dbTable.getSubSystem());
        for (String name : existingTables) {
            Pair<String, String> tablePair = tableModuleDefinition.findTablePair(name);
            String tableID = tablePair.getFirstMember();
            if (tableID != null)
                enums.add(MDBTableEnum.builder()
                        .attribute(name)
                        .number(Integer.parseInt(tableID))
                        .build());
        }
        Collections.sort(enums);
        return enums.toArray(new MDBTableEnum[enums.size()]);
    }

    private MDBTableEnum assembleTableModuleLastEnum(DBTable dbTable) {
        String tableID = tableModuleDefinition.findTableID(dbTable.getTableFileName());
        if (tableID != null)
            return MDBTableEnum.builder()
                    .attribute(dbTable.getName())
                    .number(Integer.parseInt(tableID)).build();
        return null;
    }

    private String[] assembleCommonIndexOrder(DBTable dbTable) {
        List<String> indexOrders = new ArrayList<>();
        TableIndex[] indexes = dbTable.getIndexes();
        for (int i = 0; i < indexes.length; i++) {
            if (!indexes[i].getIndexName().equalsIgnoreCase(PK_INDEX_NAME)) {
                indexOrders.add(indexes[i].getNo());
            }
        }
        return indexOrders.toArray(new String[indexOrders.size()]);
    }

    private TableField[] assembleTableFields(DBTable dbTable) {
        List<TableField> retField = new ArrayList<>();
        TableField[] tFields = dbTable.getFields();
        for (int i = 0; i < tFields.length; i++) {
            TableField field = tFields[i];
            String type = buildSingleBasicType(field.getName());
            if (type.equalsIgnoreCase("char")) {
                retField.add(TableField.builder()
                        .type(type)
                        .name(field.getName())
                        .length("[" + (Integer.parseInt(field.getLength()) + 1) + "]")
                        .build());
            } else {
                retField.add(TableField.builder()
                        .type(type)
                        .name(field.getName())
                        .length("")
                        .build());
            }
        }
        return retField.toArray(new TableField[retField.size()]);
    }

    private DBTableCxx.TableIndexCxx[] assembleTableIndexes(DBTable dbTable) {
        List<DBTableCxx.TableIndexCxx> tIndexes = new ArrayList<>();
        TableIndex[] indexes = dbTable.getIndexes();
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i].getIndexName().equalsIgnoreCase(PK_INDEX_NAME)) {
                tableIndexBuilder.buildIndex(indexes[i]);
            }
        }
        return tIndexes.toArray(new DBTableCxx.TableIndexCxx[tIndexes.size()]);
    }

    private String buildSingleBasicType(String field) {
        StdtypeType stdType = fieldsRetriever.findField(field.trim());
        String structType = BoostTypeConverter.convert2Struct(BasicType.valueOf(stdType.getBasicType()));
        return structType;
    }

    @Override
    public DBTableCxx assemble(String subSystem, String moduleName, String destFileName, DBTable tableType) {
        log.warn("No implement");
        return null;
    }
}
