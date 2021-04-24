package com.cjhxfund.autocode.wesklake.parse.db;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.ArrayOfSequenceType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class DBSequenceParser implements XmlFileParser<ArrayOfSequenceType> {

    @Override
    public ArrayOfSequenceType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        ArrayOfSequenceType sequenceType = (ArrayOfSequenceType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.COMMON_XML_FILE_RESOURCE
                        + interfaceFileName));
        return sequenceType;
    }

    @Override
    public ArrayOfSequenceType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (ArrayOfSequenceType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public ArrayOfSequenceType parse(InputStream inputStream, Class clazz) throws JAXBException {
        return null;
    }
}
