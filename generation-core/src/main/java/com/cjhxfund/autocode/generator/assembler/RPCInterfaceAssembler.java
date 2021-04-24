package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.cache.SystemFieldsRetriever;
import com.cjhxfund.autocode.model.BasicType;
import com.cjhxfund.autocode.model.converter.FieldTypeConverter;
import com.cjhxfund.autocode.model.out.proto.*;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.StdfieldType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.StdtypeType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.system.SubsystemType;
import com.cjhxfund.autocode.wesklake.model.xsd.rpc.IntfParamFieldType;
import com.cjhxfund.autocode.wesklake.model.xsd.rpc.IntfParamType;
import com.cjhxfund.autocode.wesklake.model.xsd.rpc.ParamFieldsType;
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
public class RPCInterfaceAssembler implements ContentFileAssembler<ProtoFlatContent, ServiceInterfaceType> {

    private final static String RPC_INTERFACE_IN_PARAM = "0";
    private final static String RPC_INTERFACE_OUT_PARAM = "1";
    private final static String TRADE_DEFAULT_PARENT_PACKAGE = "invest.intf";

    @Autowired
    private SystemFieldsRetriever fieldsRetriever;

    @Override
    public ProtoFlatContent assemble(String subSystem, String moduleName, String fileName, ServiceInterfaceType interfaceType) {
        log.info("Assembling RPC proto file {} for module {}", fileName, moduleName);
        ProtoFlatContent protoFlatContent = ProtoFlatContent
                .builder()
                .protoFileName(fileName)
                .subSystem(subSystem)
                .moduleName(moduleName)
                .build();
        protoFlatContent.setPackageName(TRADE_DEFAULT_PARENT_PACKAGE + "." + findSubSystemEnName(subSystem));
        RpcMessage[] messages = assembleMessages(interfaceType);
        protoFlatContent.setMessages(assembleMessages(interfaceType));
        protoFlatContent.setInterfaces(assembleInterface(interfaceType, messages));
        return protoFlatContent;
    }

    private RpcMessage[] assembleMessages(ServiceInterfaceType interfaceType) {
        List<RpcMessage> rpcMessages = new ArrayList<>();
        List<IntfParamType> inParamNames = interfaceType.getInParams().getIntfParam();
        for (IntfParamType param : inParamNames) {
            rpcMessages.add(assembleParam(param, RPC_INTERFACE_IN_PARAM));
        }

        IntfParamType outParamName = interfaceType.getOutParams().getIntfParam();
        rpcMessages.add(assembleParam(outParamName, RPC_INTERFACE_OUT_PARAM));
        return rpcMessages.toArray(new RpcMessage[rpcMessages.size()]);
    }

    private RpcMessage assembleParam(IntfParamType paramType, String type) {
        RpcMessage rpcMessage = RpcMessage.builder()
                .name(paramType.getName())
                .type(type)
                .build();
        List<RpcMessageStruct> structParams = buildStructs(paramType);
        if (type.equalsIgnoreCase("0")) {
            rpcMessage.setStructs(structParams.toArray(new RpcMessageStruct[structParams.size()]));
        } else {
            rpcMessage.setStructs(structParams.toArray(new RpcMessageStruct[structParams.size()]));
        }
        return rpcMessage;
    }

    private List<RpcMessageStruct> buildStructs(IntfParamType paramType) {
        ParamFieldsType paramFieldsType = paramType.getParamFields();
        List<IntfParamFieldType> interfaceParams = paramFieldsType.getIntfParamField();
        int paramOrder = 0;
        List<RpcMessageStruct> paramList = new ArrayList<>();
        for (IntfParamFieldType fieldType : interfaceParams) {
            String name = fieldType.getName();
            paramOrder += 1;
            StdfieldType field = fieldsRetriever.findFieldTypeInfo(name);
            StdtypeType type = fieldsRetriever.findField(name);
            String basicType = FieldTypeConverter.convert2Message(BasicType.valueOf(type.getBasicType()));
            paramList.add(RpcMessageStruct.builder()
                    .structName(name)
                    .type(basicType)
                    .name(name)
                    .comment(field != null ? field.getChName() : "")
                    .orderNo(paramOrder)
                    .build());
        }
        return paramList;
    }

    private RpcInterface assembleInterface(ServiceInterfaceType interfaceType, RpcMessage[] messages) {
        RpcInterface rpcInterface = RpcInterface.builder()
                .interfaceName(interfaceType.getSummary().getName())
                .build();
        List<RpcInterfaceStruct> reqList = new ArrayList<>();
        List<RpcInterfaceStruct> respList = new ArrayList<>();
        int reqOrder = 0;
        int respOrder = 0;
        for (RpcMessage message : messages) {
            if (message.getType().equalsIgnoreCase("0")) {
                reqOrder += 1;
                reqList.add(RpcInterfaceStruct.builder()
                        .name("list" + reqOrder)
                        .type(message.getName())
                        .orderNo(String.valueOf(reqOrder))
                        .build());
            } else {
                respOrder += 1;
                respList.add(RpcInterfaceStruct.builder()
                        .name("list" + respOrder)
                        .type(message.getName())
                        .orderNo(String.valueOf(respOrder))
                        .build());
            }
        }
        rpcInterface.setRequestList(reqList.toArray(new RpcInterfaceStruct[reqList.size()]));
        rpcInterface.setResponseList(respList.toArray(new RpcInterfaceStruct[respList.size()]));
        return rpcInterface;
    }

    private String findSubSystemEnName(String subSystem) {
        SubsystemType subsystemType = fieldsRetriever.findSubSystem(subSystem);
        if (subsystemType == null)
            throw new RuntimeException("Subsystem is not defined");
        return subsystemType.getEnName();
    }
}
