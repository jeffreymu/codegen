package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.model.out.table.*;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableType;
import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DBTableGenTest {

    public static void generateTable(TableType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);

        List<TableField> tableFields = new ArrayList<>();
        tableFields.add(TableField.builder()
                .name("ins_op_id")
                .type("number")
                .length("10")
                .defaultValue("0")
                .nullable("not null")
                .build());
        tableFields.add(TableField.builder()
                .name("ins_sec_id")
                .type("number")
                .length("10")
                .defaultValue("0")
                .nullable("not null")
                .build());
        TableField lastField = TableField.builder()
                .name("ins_id")
                .type("number")
                .length("10")
                .precision("2")
                .defaultValue("' '")
                .nullable("not null")
                .build();

        List<TableIndex> tableIndexList = new ArrayList<>();
        TableIndex index = TableIndex.builder()
                .indexName("pk_index")
                .fieldName("ins_op_id")
                .build();
        tableIndexList.add(TableIndex.builder()
                .indexName("test_index1")
                .fieldName("ins_id")
                .build());
        tableIndexList.add(TableIndex.builder()
                .indexName("test_index2")
                .fieldName("combi_id")
                .build());

        List<TableField> tFields = new ArrayList<>();
        tFields.add(TableField.builder()
                .name("creation_share")
                .type("double")
                .length("")
                .build());
        tFields.add(TableField.builder()
                .name("deal_qty")
                .type("double")
                .length("")
                .build());
        tFields.add(TableField.builder()
                .name("time_stamp")
                .type("char")
                .length("[33]")
                .build());

        List<String> indexLens = new ArrayList<>();
        indexLens.add("0");
        indexLens.add("1");

//        TableIndexCxx pkIndex = TableIndexCxx.builder()
//                .no("0")
//                .fieldName("const char *etf_securitycode2, const char *time_stamp")
//                .build();
//        List<TableIndexCxx> tIndexes = new ArrayList<>();
//        tIndexes.add(TableIndex.builder()
//                .indexName("const char *etf_securitycode2, const char *time_stamp")
//                .build());
//        tIndexes.add(TableIndexCxx.builder()
//                .no("1")
//                .fieldName("const char *etf_securitycode2")
//                .build());
//        DBTable dbTable = DBTable.builder()
//                .name("tinsextramodify")
//                .tableModuleName("etfmonitor")
//                .shadowName("zinsextramodify")
//                .fields(tableFields.toArray(new TableField[tableFields.size()]))
//                .lastField(lastField)
//                .pkIndex(index)
//                .indexes(tableIndexList.toArray(new TableIndex[tableIndexList.size()]))
//                .build();

//        DBTableCxx dbTablex = DBTableCxx.builder()
//                .hashKey("#")
//                .name("tinsextramodify")
//                .upperTableName("tinsextramodify".toUpperCase())
//                .tableModuleName("etfmonitor")
//                .upperTableModuleName("etfmonitor".toUpperCase())
//                .pkIndexParam(pkIndex.getFieldName())
//                .tableIndexes(tIndexes.toArray(new TableIndexCxx[tIndexes.size()]))
//                .tableFields(tFields.toArray(new TableField[tFields.size()]))
//                .indexOrders(indexLens.toArray(new String[indexLens.size()]))
//                .build();
        sTemplate.add("params", null);
        log.info(sTemplate.render());
    }

    public static void main(String[] args) throws JAXBException {

        DBTableGenTest.generateTable(null, "st/mdb/mdb-table-hxx.stg", "mdbTableHeaderFileTemplate");

    }
}
