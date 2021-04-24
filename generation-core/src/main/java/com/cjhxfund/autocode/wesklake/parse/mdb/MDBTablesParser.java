package com.cjhxfund.autocode.wesklake.parse.mdb;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.ArrayOfMDBTableType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/15.
 */
public class MDBTablesParser implements XmlFileParser<ArrayOfMDBTableType> {

    @Override
    public ArrayOfMDBTableType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        ArrayOfMDBTableType mdbTable = (ArrayOfMDBTableType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.COMMON_XML_FILE_RESOURCE
                        + interfaceFileName));
        return mdbTable;
    }

    @Override
    public ArrayOfMDBTableType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (ArrayOfMDBTableType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public ArrayOfMDBTableType parse(InputStream inputStream, Class clazz) throws JAXBException {
        return null;
    }

}
