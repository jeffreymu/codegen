package com.cjhxfund.autocode.wesklake.parse.rpc;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.rpc.ServiceInterfaceType;
import com.cjhxfund.autocode.wesklake.parse.XmlFileParser;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class RpcInterfaceParser implements XmlFileParser<ServiceInterfaceType> {

    @Override
    public ServiceInterfaceType parse(String interfaceFileName, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        String path = Class.class.getClass().getResource("/").getPath();
        ServiceInterfaceType serviceInterface = (ServiceInterfaceType) unmarshaller.unmarshal(
                new File(path
                        + WestLakeSourceFileConfig.RPC_INTERFACE_XML_FILE_RESOURCE
                        + interfaceFileName));
        return serviceInterface;
    }

    @Override
    public ServiceInterfaceType parse(File fileFullPath, Class clazz) throws JAXBException {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return (ServiceInterfaceType) unmarshaller.unmarshal(fileFullPath);
    }

    @Override
    public ServiceInterfaceType parse(InputStream inputStream, Class clazz) throws JAXBException {
        return null;
    }

}
