package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.model.out.mdb.*;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.ArrayOfMDBTableType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.MDBTableType;
import com.cjhxfund.autocode.wesklake.model.xsd.table.FieldsType;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableFieldType;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableType;
import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/15.
 */
@Slf4j
public class MDBTableGeneratorTest {

    private static MDBTableType findByName(List<MDBTableType> mdbTables, String tableCnName) {
        for (MDBTableType table : mdbTables) {
            if (tableCnName.equals(table.getChName())) {
                return table;
            }
        }
        throw new RuntimeException("Can't find any MDB table");
    }

    private static MDBTableType findByID(List<MDBTableType> mdbTables, String tableID) {
        for (MDBTableType table : mdbTables) {
            if (tableID.equals(table.getId())) {
                return table;
            }
        }
        throw new RuntimeException("Can't find any MDB table");
    }

    public static void generateTableRTTI(TableType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);

        FieldsType fieldsType = result.getFields();
        List<TableFieldType> fields = fieldsType.getTableField();
        for (TableFieldType field : fields) {
        }

        MDBTable mdbTable = MDBTable.builder()
                .hashKey("#")
                .name("test")
                .module("trade")
                .namespace("rxmdb_trade")
                .upperModule("trade".toUpperCase())
                .build();
        sTemplate.add("params", mdbTable);
        log.info(sTemplate.render());
    }

    public static void generateTableEnum(TableType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);
        FieldsType fieldsType = result.getFields();
        List<MDBTableEnum> enums = new ArrayList<>();
        enums.add(MDBTableEnum.builder()
                .attribute("ZHENGCHANGDAOZHANG")
                .number(1)
                .build());
        enums.add(MDBTableEnum.builder()
                .attribute("ZHIYA")
                .number(2)
                .build());

        MDBTable mdbTable = MDBTable.builder()
                .hashKey("#")
                .name("test")
                .module("trade")
                .namespace("rxmdb_trade")
                .upperModule("trade".toUpperCase())
                .lastEnum(MDBTableEnum.builder()
                        .attribute("JITA")
                        .number(5)
                        .build())
                .enums(enums.toArray(new MDBTableEnum[enums.size()]))
                .build();
        sTemplate.add("params", mdbTable);
        log.info(sTemplate.render());
    }

    public static void generateBizHeaderFile(TableType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);
        List<MDBSubTable> tables = new ArrayList<>();
        tables.add(MDBSubTable.builder().name("textinqdealsync").build());
        tables.add(MDBSubTable.builder().name("talgo_exec_status").build());
        MDBTable mdbTable = MDBTable.builder()
                .hashKey("#")
                .name("textinqdealsync")
                .module("trade")
                .namespace("rxmdb_trade")
                .tables(tables.toArray(new MDBSubTable[tables.size()]))
                .upperModule("trade".toUpperCase())
                .build();
        sTemplate.add("params", mdbTable);
        log.info(sTemplate.render());
    }

    public static void generateBizImplCppFile(TableType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);
        List<MDBSubTable> tables = new ArrayList<>();
        tables.add(MDBSubTable.builder().name("textinqdealsync").build());
        tables.add(MDBSubTable.builder().name("talgo_exec_status").build());

        List<MDBSequence> sequences = new ArrayList<>();
        sequences.add(MDBSequence.builder()
                .orderId(1)
                .name("seq_order_id")
                .start(1)
                .flag1("true")
                .flag2("false")
                .build());

        sequences.add(MDBSequence.builder()
                .orderId(2)
                .name("seq_order_batch_id")
                .start(1)
                .flag1("false")
                .flag2("true")
                .build());

        MDBTable mdbTable = MDBTable.builder()
                .hashKey("#")
                .name("textinqdealsync")
                .module("trade")
                .namespace("rxmdb_trade")
                .tables(tables.toArray(new MDBSubTable[tables.size()]))
                .sequences(sequences.toArray(new MDBSequence[sequences.size()]))
                .upperModule("trade".toUpperCase())
                .build();
        sTemplate.add("params", mdbTable);
        log.info(sTemplate.render());
    }

    public static void generateBizRttiImplCppFile(TableType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);
        List<MDBSubTable> tables = new ArrayList<>();
        List<MDBTableField> fieldList = new ArrayList<>();
        fieldList.add(MDBTableField.builder()
                .id(54)
                .name("busin_name")
                .type("STRING")
                .len(257)
                .pos(0)
                .build());
        fieldList.add(MDBTableField.builder()
                .id(54)
                .name("mkt_id_list")
                .type("STRING")
                .len(257)
                .pos(257)
                .build());

        List<MDBTableIndex> indexList = new ArrayList<>();
        indexList.add(MDBTableIndex.builder()
                .id(0)
                .tableId(81)
                .flag("true")
                .fields("pos_str, capital_account_id")
                .build());
        indexList.add(MDBTableIndex.builder()
                .id(10)
                .tableId(81)
                .flag("true")
                .fields("capital_account_id")
                .build());
        List<MDBTableIndex> indexList1 = new ArrayList<>();
        indexList1.add(MDBTableIndex.builder()
                .id(10)
                .tableId(81)
                .flag("true")
                .fields("capital_account_id")
                .build());;
        tables.add(MDBSubTable.builder()
                .id(81)
                .name("tbranchstock")
                .size(650)
                .tableFlag("table")
                .flag1("true")
                .flag2("true")
                .flag3("true")
                .comment("")
                .fields(fieldList.toArray(new MDBTableField[fieldList.size()]))
               // .indexes(indexList.toArray(new MDBTableIndex[indexList.size()]))
                .name("textinqdealsync")
                .build());

        tables.add(MDBSubTable.builder()
                .id(81)
                .name("tbranchstock")
                .size(650)
                .tableFlag("table")
                .flag1("true")
                .flag2("true")
                .flag3("true")
                .comment("")
               // .indexes(indexList1.toArray(new MDBTableIndex[indexList1.size()]))
                .name("talgo_exec_status")
                .build());

        List<MDBSequence> sequences = new ArrayList<>();
        sequences.add(MDBSequence.builder()
                .orderId(1)
                .name("seq_order_id")
                .start(1)
                .flag1("true")
                .flag2("false")
                .build());

        sequences.add(MDBSequence.builder()
                .orderId(2)
                .name("seq_order_batch_id")
                .start(1)
                .flag1("false")
                .flag2("true")
                .build());

        MDBTable mdbTable = MDBTable.builder()
                .hashKey("#")
                .name("textinqdealsync")
                .module("trade")
                .namespace("rxmdb_trade")
                .tables(tables.toArray(new MDBSubTable[tables.size()]))
                .sequences(sequences.toArray(new MDBSequence[sequences.size()]))
                .upperModule("trade".toUpperCase())
                .build();
        sTemplate.add("params", mdbTable);
        log.info(sTemplate.render());
    }

    public static void main(String[] args) throws JAXBException {
        ArrayOfMDBTableType result = null;
//        MDBTablesParser.parse(
//                "mdb_table.xml", ArrayOfMDBTableType.class);

        List<MDBTableType> mdbTables = result.getMDBTable();
        log.info("size= " + mdbTables.size());
        MDBTableType targetMDBTable = findByID(mdbTables, "668");
        String tableXmlFileName = "table_" + targetMDBTable.getChName() + ".xml";
//        TableType tableType = DBTableParser.parse(tableXmlFileName, TableType.class);
//        log.info("enName: " + tableType.getEnname());

//        MDBTableGeneratorTest.generateTableRTTI(tableType, "st/mrtti-hxx.stg", "mrttiTemplate");
//        MDBTableGeneratorTest.generateTableEnum(tableType, "st/mdb-table-enum.stg", "enumTemplate");
//        MDBTableGeneratorTest.generateBizHeaderFile(tableType, "st/mdb-biz-hxx.stg", "heardFileTemplate");
//        MDBTableGeneratorTest.generateBizHeaderFile(tableType, "st/mdb-biz-impl-hxx.stg", "heardFileTemplate");
//        MDBTableGeneratorTest.generateBizImplCppFile(tableType, "st/mdb-biz-impl-cxx.stg", "bizImplFileTemplate");
//        MDBTableGeneratorTest.generateBizRttiImplCppFile(tableType, "st/mdb/mdb-rtti-impl-cxx.stg", "rttiImplFileTemplate");
    }
}
