package com.cjhxfund.autocode.builder;

import com.cjhxfund.autocode.wesklake.model.xsd.mysql.*;
import com.sun.xml.internal.bind.marshaller.DataWriter;
import com.sun.xml.internal.bind.marshaller.DumbEscapeHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
public class MySQLXmlBuilderTest {

    private TableType create() throws Exception {

        ObjectFactory factory = new ObjectFactory();
        TableType tableType = factory.createTableType();
        tableType.setEnname("tetfmproduct");
        tableType.setChname("ETF产品表");
        factory.createTable(tableType);

        ColumnsType columnsType = factory.createColumnsType();
        List<ItemType> colItems = columnsType.getItem();
        ItemType item = new ItemType();
        item.setName("product_id");
        item.setType("INT32");
        item.setLength("10");
        item.setPrecision("0");
        item.setNullable("n");
        item.setDefaultValue("");
        colItems.add(item);
        IndexsType indexType = factory.createIndexsType();
        List<ItemType> indItems = indexType.getItem();
        ItemType item0 = new ItemType();
        item0.setName("pk_tetfmproduct");
        item0.setType("primary");
        item0.setLength("product_id");

        indItems.add(item0);
        tableType.setColumns(columnsType);
        tableType.setIndexs(indexType);
        return tableType;
    }

    @Test
    public void test_gen_mysql_xml() throws Exception {
        TableType tableType = create();
        JAXBContext  jaxbContext = JAXBContext.newInstance(TableType.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "​GBK");
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
        StringWriter sw = new StringWriter();
        PrintWriter printWriter = new PrintWriter(System.out);
        DataWriter dataWriter = new DataWriter(printWriter, "​GBK", DumbEscapeHandler.theInstance);
        jaxbMarshaller.marshal(tableType, sw);
         String xmlContent = sw.toString();
        log.info(xmlContent);
    }

    @Test
    public void test_gen_xml_4_chinese() throws Exception {
        TableType tableType = create();
        JAXBContext jc = JAXBContext.newInstance(tableType.getClass());
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_ENCODING, "gb2312");
        m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Result result = new StreamResult(out);
        m.marshal(tableType, result);
        byte[] bystr = out.toByteArray();
        String str = "";
        try {
            str = new String(bystr,"gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer(str.replace("<?xml version=\"1.0\" encoding=\"gb2312\" standalone=\"yes\"?>",
                "<?xml version=\"1.0\" encoding=\"gb2312\"?>"));
        log.info(sb.toString());

    }

}
