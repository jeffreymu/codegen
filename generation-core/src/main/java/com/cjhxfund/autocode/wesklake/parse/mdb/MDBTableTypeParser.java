package com.cjhxfund.autocode.wesklake.parse.mdb;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.ArrayOfMDBType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/15.
 */
public class MDBTableTypeParser implements XmlFileParser<ArrayOfMDBType> { ;

    @Override
    public ArrayOfMDBType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        ArrayOfMDBType mdbType = (ArrayOfMDBType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.COMMON_XML_FILE_RESOURCE
                        + interfaceFileName));
        return mdbType;
    }

    @Override
    public ArrayOfMDBType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (ArrayOfMDBType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public ArrayOfMDBType parse(InputStream inputStream, Class clazz) throws JAXBException {
        return null;
    }

}
