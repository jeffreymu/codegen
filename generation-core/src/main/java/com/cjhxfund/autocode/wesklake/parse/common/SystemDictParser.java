package com.cjhxfund.autocode.wesklake.parse.common;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.ArrayOfDictType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class SystemDictParser implements XmlFileParser<ArrayOfDictType>  {

    @Override
    public ArrayOfDictType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        ArrayOfDictType arrayOfDict = (ArrayOfDictType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.COMMON_XML_FILE_RESOURCE
                        + interfaceFileName));
        return arrayOfDict;
    }

    @Override
    public ArrayOfDictType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (ArrayOfDictType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public ArrayOfDictType parse(InputStream inputStream, Class clazz) throws JAXBException {
        return null;
    }


}
