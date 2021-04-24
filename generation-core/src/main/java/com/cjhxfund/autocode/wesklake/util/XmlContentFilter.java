package com.cjhxfund.autocode.wesklake.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public final class XmlContentFilter {

    private final static int DATA_XML_SCHEMA_LENGTH = 16;

    public static String filter(String fileName) throws IOException {
        String xmlContent = FileUtils.readFileToString(new File(fileName), "UTF-8");
        String changedContent = xmlContent.replace("&lt;", "<");
        changedContent = changedContent.replace("&gt;", ">");
        int startPos = changedContent.indexOf("<DataXmlSchema>");
        int endPos = changedContent.indexOf("</DataXmlSchema>");
        String dataXmlSchema1 = changedContent.substring(0, startPos);
        String dataXmlSchema2 = changedContent.substring(endPos + DATA_XML_SCHEMA_LENGTH);
        return dataXmlSchema1 + dataXmlSchema2;
    }

}
