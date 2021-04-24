package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.model.out.service.RpcService;
import com.cjhxfund.autocode.model.out.service.RpcServiceRegister;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableType;
import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/15.
 */
@Slf4j
public class RpcServiceFileGeneratorTest {

    public static void generateBizSrvBaseFile(TableType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);

        RpcService rpcService = RpcService.builder()
                .hashKey("#")
                .componentName("algotempatemgr")
                .serviceName("algotemplate_add")
                .namespaceL1("longshare")
                .namespaceL2("mdbcom")
                .build();
        sTemplate.add("params", rpcService);
        log.info(sTemplate.render());
    }

    public static void generateBizSrvRegister(TableType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);
        List<RpcService> services = new ArrayList<>();
        RpcService rpcService = RpcService.builder()
                .hashKey("#")
                .componentName("algotempatemgr")
                .serviceName("algotemplate_add")
                .namespaceL1("longshare")
                .namespaceL2("mdbcom")
                .build();

        services.add(rpcService);
        services.add(RpcService.builder()
                .hashKey("#")
                .componentName("algotempatemgr")
                .serviceName("algotemplate_modify")
                .namespaceL1("longshare")
                .namespaceL2("mdbcom")
                .build());
        RpcServiceRegister register = RpcServiceRegister.builder()
                .componentName("algotempatemgr")
                .hashKey("#")
                .services(services.toArray(new RpcService[services.size()]))
                .build();

        sTemplate.add("params", register);
        log.info(sTemplate.render());
    }

    public static void main(String[] args) throws JAXBException {

//        RpcServiceFileGeneratorTest.generateBizSrvBaseFile(
//                null, "st/rpc-biz-base-hxx.stg", "heardFileTemplate");

//        RpcServiceFileGeneratorTest.generateBizSrvBaseFile(
//                null, "st/rpc-biz-base-cxx.stg", "serviceRegTemplate");

        RpcServiceFileGeneratorTest.generateBizSrvRegister(
                null, "st/rpc/rpc-biz-register-cxx.stg", "serviceRegTemplate");
    }
}
