package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.model.out.proto.*;
import com.cjhxfund.autocode.wesklake.model.xsd.rpc.ServiceInterfaceType;
import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/14.
 */
@Slf4j
public class ProtoFileGeneratorTest {

    public static void generate(ServiceInterfaceType definedResult, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);
//        IntfParamType intfParamType = definedResult.getInParams().getIntfParam();
//        String interfaceName = intfParamType.getName();
//        intfParamType.getParamFields();

        List<RpcMessage> messages = new ArrayList<>();

        List<RpcMessageStruct> reqMessages = new ArrayList<>();
        reqMessages.add(RpcMessageStruct.builder()
                .structName("request_ins_available_detail_qry_p1")
                .type("int32")
                .name("unit_id")
                .orderNo(1)
                .comment("单元序号")
                .build());
        reqMessages.add(RpcMessageStruct.builder()
                .structName("response_ins_available_detail_qry_p2")
                .type("int32")
                .name("mkt_id")
                .orderNo(2)
                .comment("市场序号")
                .build());

        List<RpcMessageStruct> respMessages = new ArrayList<>();
        respMessages.add(RpcMessageStruct.builder()
                .structName("response_ins_available_detail_qry_p1")
                .type("int32")
                .name("unit_id")
                .orderNo(1)
                .comment("单元序号")
                .build());

        List<RpcInterfaceStruct> inStruts = new ArrayList<>();
        inStruts.add(RpcInterfaceStruct.builder()
                .type("request_ins_pendingsettle_qry_p1")
                .name("list1")
                .type("request_fix_ins_order_deal_p1")
                .orderNo("1")
                .build());

        inStruts.add(RpcInterfaceStruct.builder()
                .type("request_ins_pendingsettle_qry_p2")
                .name("list2")
                .type("request_fix_ins_order_deal_p2")
                .orderNo("2")
                .build());

        List<RpcInterfaceStruct> outStruts = new ArrayList<>();
        outStruts.add(RpcInterfaceStruct.builder()
                .type("response_fix_ins_order_deal_p1")
                .name("list1")
                .orderNo("1")
                .build());

        RpcInterface rpcInterface = RpcInterface.builder()
                .interfaceName("ins_pendingsettle_qry")
                .requestList(inStruts.toArray(new RpcInterfaceStruct[inStruts.size()]))
                .responseList(outStruts.toArray(new RpcInterfaceStruct[outStruts.size()]))
                .build();

        List<RpcMessage> rpcMessages = new ArrayList<>();
        RpcMessage rpcMessage = RpcMessage.builder()
                .type("rpcInterface")
                .name("request_ins_pendingsettle_qry_p1")
                .structs(reqMessages.toArray(new RpcMessageStruct[reqMessages.size()]))
                .build();
        rpcMessages.add(rpcMessage);
        ProtoFlatContent protoFlatContent = ProtoFlatContent.builder()
                .packageName("invest.intf")
                .interfaces(rpcInterface)
                .messages(rpcMessages.toArray(new RpcMessage[reqMessages.size()]))
                .build();
        sTemplate.add("params", protoFlatContent);
        log.info(sTemplate.render());

    }

    public static void main(String[] args) throws JAXBException {
        ServiceInterfaceType result = null;
//        RpcInterfaceParser.parse(
//                "interface_指令可用查询.xml", ServiceInterfaceType.class);
        ProtoFileGeneratorTest.generate(result, "st/proto/proto-file.stg", "protoTemplate");
    }
}
