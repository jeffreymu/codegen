package com.cjhxfund.autocode.wesklake.parse.syserror;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.error.ArrayOfSystemErrorType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class SystemErrorParser implements XmlFileParser<ArrayOfSystemErrorType> {

    @Override
    public ArrayOfSystemErrorType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        ArrayOfSystemErrorType systemErrorType = (ArrayOfSystemErrorType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.COMMON_XML_FILE_RESOURCE
                        + interfaceFileName));
        return systemErrorType;
    }

    @Override
    public ArrayOfSystemErrorType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (ArrayOfSystemErrorType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public ArrayOfSystemErrorType parse(InputStream inputStream, Class clazz) throws JAXBException {
        return null;
    }

}
