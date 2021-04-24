package com.cjhxfund.autocode.wesklake.parse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/21.
 */
public interface XmlFileParser<T> {

    T parse(String interfaceFileName, Class clazz) throws JAXBException;

    T parse(File fileFullPath, Class clazz) throws JAXBException;

    T parse(InputStream inputStream, Class clazz) throws JAXBException;

    default Unmarshaller createUnmarshaller(Class clazz) throws JAXBException {
        return JAXBContext.newInstance(clazz).createUnmarshaller();
    }
}
