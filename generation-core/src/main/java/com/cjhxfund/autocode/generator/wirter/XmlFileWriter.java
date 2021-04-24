package com.cjhxfund.autocode.generator.wirter;

import com.cjhxfund.autocode.wesklake.model.xsd.mysql.TableType;
import com.cjhxfund.autocode.wesklake.model.xsd.mysql.init.DataType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class XmlFileWriter {

    private final static String XML_DEFAULT_ENCODING = "GBK";
    private final static String XML_GBK_FORMAT_STD_HEADER = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?>";
    private final static String XML_GBK_FORMAT_HEADER = "<?xml version=\"1.0\" encoding=\"GBK\"?>";

    public static void write(TableType tableType, String fileName) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TableType.class);
        Marshaller jaxbMarshaller = createMarshaller(jaxbContext);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Result result = new StreamResult(out);
        jaxbMarshaller.marshal(tableType, result);
        byte[] outBytes = out.toByteArray();
        String xmlContent = new String(outBytes, XML_DEFAULT_ENCODING);
        StringBuffer buffer = new StringBuffer(xmlContent.replace(XML_GBK_FORMAT_STD_HEADER, XML_GBK_FORMAT_HEADER));
        ContentFileWriter.writeByCharset(fileName, buffer.toString(),
                java.nio.charset.Charset.forName(XML_DEFAULT_ENCODING));
    }

    public static void writeData(DataType dataType, String fileName) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(DataType.class);
        Marshaller jaxbMarshaller = createMarshaller(jaxbContext);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Result result = new StreamResult(out);
        jaxbMarshaller.marshal(dataType, result);
        byte[] outBytes = out.toByteArray();
        String xmlContent = new String(outBytes, XML_DEFAULT_ENCODING);
        String changedContent = "";
        if (xmlContent.contains("&quot;")) {
            changedContent = xmlContent.replaceAll("&quot;", "\\\\&quot;");
        }
        StringBuffer buffer = new StringBuffer(changedContent.replace(XML_GBK_FORMAT_STD_HEADER, XML_GBK_FORMAT_HEADER));
        ContentFileWriter.writeByCharset(fileName, buffer.toString(),
                java.nio.charset.Charset.forName(XML_DEFAULT_ENCODING));
    }

    private static Marshaller createMarshaller(JAXBContext jaxbContext) throws JAXBException {
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, XML_DEFAULT_ENCODING);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
        return jaxbMarshaller;
    }
}
