package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.model.out.table.DBTable;
import com.cjhxfund.autocode.model.out.table.TableField;
import com.cjhxfund.autocode.model.out.table.TableIndex;
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
 * Created by Jeffrey on 2021/3/14.
 */
@Slf4j
public class DBTableGeneratorTest {

    public static void generateTable(TableType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);

        FieldsType fieldsType = result.getFields();
        List<TableFieldType> fields = fieldsType.getTableField();
        for (TableFieldType field : fields) {
        }

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
        DBTable dbTable = DBTable.builder()
                .name("tinsextramodify")
                .shadowName("zinsextramodify")
                .fields(tableFields.toArray(new TableField[tableFields.size()]))
                .lastField(lastField)
                .pkIndex(index)
                .indexes(tableIndexList.toArray(new TableIndex[tableIndexList.size()]))
                .build();

        sTemplate.add("params", dbTable);
        log.info(sTemplate.render());
    }

    public static void generateHistoryTable(TableType result, String stFile, String templateName) {
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
        DBTable dbTable = DBTable.builder()
                .name("thisinsextramodify")
                .shadowName("zhisinsextramodify")
                .fields(tableFields.toArray(new TableField[tableFields.size()]))
                .lastField(lastField)
                .pkIndex(index)
                .indexes(tableIndexList.toArray(new TableIndex[tableIndexList.size()]))
                .build();

        sTemplate.add("params", dbTable);
        log.info(sTemplate.render());
    }

    public static void main(String[] args) throws JAXBException {
        TableType result = null;
//        DBTableParser.parse(
//                "table_指令修改附加信息表.xml", TableType.class);
        DBTableGeneratorTest.generateTable(result, "st/db/db-table.stg", "tableTemplate");
        DBTableGeneratorTest.generateHistoryTable(result, "st/db/history-table.stg", "hisTableTemplate");

    }
}
