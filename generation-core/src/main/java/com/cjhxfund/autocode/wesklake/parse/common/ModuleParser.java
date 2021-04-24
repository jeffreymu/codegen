package com.cjhxfund.autocode.wesklake.parse.common;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.ModulePropertyType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class ModuleParser implements XmlFileParser<ModulePropertyType> {

    @Override
    public ModulePropertyType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        ModulePropertyType modulePropertyType = (ModulePropertyType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.DB_TABLE_XML_FILE_RESOURCE
                        + interfaceFileName));
        return modulePropertyType;
    }

    @Override
    public ModulePropertyType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (ModulePropertyType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public ModulePropertyType parse(InputStream inputStream, Class clazz) throws JAXBException {
        return null;
    }
}

