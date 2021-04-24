package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.cache.SystemFieldsRetriever;
import com.cjhxfund.autocode.model.out.service.RpcService;
import com.cjhxfund.autocode.model.out.service.RpcServiceRegister;
import com.cjhxfund.autocode.scanner.RPCInterfaceDefinition;
import com.cjhxfund.autocode.wesklake.model.xsd.common.system.SubsystemType;
import com.cjhxfund.autocode.wesklake.model.xsd.rpc.ServiceInterfaceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class RPCServiceCxxAssembler implements ContentFileAssembler<RpcServiceRegister, ServiceInterfaceType> {

    private final static String CXX_FILE_HASH_KEY = "#";
    private final static String SERVICE_NAMESPACE_L1 = "longshare";
    private final static String SERVICE_NAMESPACE_L2 = "mdbcom";

    @Autowired
    private SystemFieldsRetriever fieldsRetriever;

    @Autowired
    private RPCInterfaceDefinition rpcInterfaceDefinition;

    @Override
    public RpcServiceRegister assemble(String subSystem, String moduleName, String fileName, ServiceInterfaceType interfaceType) {
        log.info("Assembling RPC service {}", moduleName);
        RpcServiceRegister rpcServiceRegister = RpcServiceRegister
                .builder()
                .hashKey(CXX_FILE_HASH_KEY)
                .build();

        rpcServiceRegister.setModuleName(fieldsRetriever.findSubSystem(subSystem).getEnName());
        rpcServiceRegister.setComponentName(moduleName);
        rpcServiceRegister.setServiceName(interfaceType.getSummary().getName());
        rpcServiceRegister.setServices(assembleServices(moduleName, interfaceType));
        return rpcServiceRegister;
    }

    private RpcService[] assembleServices(String moduleName, ServiceInterfaceType interfaceType) {
        List<RpcService> rpcServices = new ArrayList<>();
        String newServiceName = interfaceType.getSummary().getName();
        List<String> interfaces = rpcInterfaceDefinition.findRPCInterfacesOfModule(moduleName);
        interfaces.stream().forEach( service -> {
            rpcServices.add(RpcService.builder()
                    .hashKey(CXX_FILE_HASH_KEY)
                    .componentName(moduleName)
                    .serviceName(service)
                    .namespaceL1(SERVICE_NAMESPACE_L1)
                    .namespaceL2(SERVICE_NAMESPACE_L2)
                    .build());
        });
        rpcServices.add(RpcService.builder()
                .hashKey(CXX_FILE_HASH_KEY)
                .componentName(moduleName)
                .serviceName(newServiceName)
                .namespaceL1(SERVICE_NAMESPACE_L1)
                .namespaceL2(SERVICE_NAMESPACE_L2)
                .build());

        return rpcServices.toArray(new RpcService[rpcServices.size()]);
    }

    private String findSubSystemEnName(String subSystem) {
        SubsystemType subsystemType = fieldsRetriever.findSubSystem(subSystem);
        if (subsystemType == null)
            throw new RuntimeException("Subsystem is not defined");
        return subsystemType.getEnName();
    }
}
