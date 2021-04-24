package com.cjhxfund.autocode.wesklake.parse.db;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.base.BasicDataType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class DBInitDataParser implements XmlFileParser<BasicDataType> {

    @Override
    public BasicDataType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        BasicDataType sequenceType = (BasicDataType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.DB_INIT_DATA_XML_FILE_RESOURCE
                        + interfaceFileName));
        return sequenceType;
    }

    @Override
    public BasicDataType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (BasicDataType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public BasicDataType parse(InputStream inputStream, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (BasicDataType) unmarshaller.unmarshal(inputStream);
    }
}
