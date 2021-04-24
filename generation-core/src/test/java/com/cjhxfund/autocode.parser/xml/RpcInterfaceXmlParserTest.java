package com.cjhxfund.autocode.parser.xml;

import com.cjhxfund.autocode.wesklake.model.xsd.rpc.ServiceInterfaceType;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;

/**
 * Created by Jeffrey on 2021/3/13.
 */

public class RpcInterfaceXmlParserTest {

    @Test
    public void test_parse_table_xml() throws JAXBException {
        String xmlFile = "interface_指令可用查询.xml";
        ServiceInterfaceType result = null;
        // RpcInterfaceParser.parse(xmlFile, ServiceInterfaceType.class);
        String acual = (result == null) ? "" : result.getSummary().getName();
        Assert.assertEquals("ins_available_qry", acual);
    }
}
