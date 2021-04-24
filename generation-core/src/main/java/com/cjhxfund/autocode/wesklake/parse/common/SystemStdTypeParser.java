package com.cjhxfund.autocode.wesklake.parse.common;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.ArrayOfStdtypeType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class SystemStdTypeParser implements XmlFileParser<ArrayOfStdtypeType>  {

    @Override
    public ArrayOfStdtypeType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        ArrayOfStdtypeType arrayOfStdtypeType = (ArrayOfStdtypeType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.COMMON_XML_FILE_RESOURCE
                        + interfaceFileName));
        return arrayOfStdtypeType;
    }

    @Override
    public ArrayOfStdtypeType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (ArrayOfStdtypeType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public ArrayOfStdtypeType parse(InputStream inputStream, Class clazz) throws JAXBException {
        return null;
    }
}
