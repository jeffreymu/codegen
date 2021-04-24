package com.cjhxfund.autocode.wesklake.parse.db;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class DBTableParser implements XmlFileParser<TableType> {

    @Override
    public TableType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        TableType tableType = (TableType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.DB_TABLE_XML_FILE_RESOURCE
                        + interfaceFileName));
        return tableType;
    }

    @Override
    public TableType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (TableType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public TableType parse(InputStream inputStream, Class clazz) throws JAXBException {
        return null;
    }
}
