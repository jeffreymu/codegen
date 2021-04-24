package com.cjhxfund.autocode.wesklake.parse.common;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.ArrayOfStdfieldType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class SystemStdFieldParser implements XmlFileParser<ArrayOfStdfieldType>  {

    @Override
    public ArrayOfStdfieldType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        ArrayOfStdfieldType arrayOfStdfieldType = (ArrayOfStdfieldType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.COMMON_XML_FILE_RESOURCE
                        + interfaceFileName));
        return arrayOfStdfieldType;
    }

    @Override
    public ArrayOfStdfieldType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (ArrayOfStdfieldType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public ArrayOfStdfieldType parse(InputStream inputStream, Class clazz) throws JAXBException {
        return null;
    }
}
