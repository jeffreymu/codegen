package com.cjhxfund.autocode.wesklake.parse.common;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.system.ArrayOfSubsystemType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class SubSystemParser implements XmlFileParser<ArrayOfSubsystemType> {

    @Override
    public ArrayOfSubsystemType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        ArrayOfSubsystemType arrayOfSubSystem = (ArrayOfSubsystemType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.COMMON_XML_FILE_RESOURCE
                        + interfaceFileName));
        return arrayOfSubSystem;
    }

    @Override
    public ArrayOfSubsystemType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (ArrayOfSubsystemType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public ArrayOfSubsystemType parse(InputStream inputStream, Class clazz) throws JAXBException {
        return null;
    }
}
