package com.cjhxfund.autocode.generator.service;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.ContentType;
import com.cjhxfund.autocode.model.out.service.RpcServiceRegister;
import com.cjhxfund.autocode.template.STGTemplates;
import com.cjhxfund.autocode.template.TemplateLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.stringtemplate.v4.ST;

import java.io.File;

@Slf4j
@Component
public class RPCServiceCxxGenerator extends BaseContentFileGenerator implements PlatFileGenerator<RpcServiceRegister> {

    private final static String SERVER_SOURCE_IMPL_FILE_PATH = "server/src/";

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Override
    public ContentFile generate(RpcServiceRegister sourceData) {
        // generate RPC register file
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.RPC_BIZ_REGISTER_CXX_STG_NAME,
                STGTemplates.RPC_BIZ_REGISTER_CXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createRPCRegisterFile(sourceData, sTemplate.render(), ContentType.RPC_SERVICE_REGISTER);

        sTemplate = stgTemplateLoader.pick(STGTemplates.RPC_BIZ_BASE_HXX_STG_NAME,
                STGTemplates.RPC_BIZ_BASE_HXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createRPCServiceFile(sourceData, sTemplate.render(), ContentType.RPC_SERVICE_HXX);

        sTemplate = stgTemplateLoader.pick(STGTemplates.RPC_BIZ_BASE_CXX_STG_NAME,
                STGTemplates.RPC_BIZ_BASE_CXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createRPCServiceFile(sourceData, sTemplate.render(), ContentType.RPC_SERVICE_CXX);

        sTemplate = stgTemplateLoader.pick(STGTemplates.RPC_BIZ_WRITE_HXX_STG_NAME,
                STGTemplates.RPC_BIZ_WRITE_HXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createRPCWriteServiceFile(sourceData, sTemplate.render(), ContentType.RPC_SERVICE_BIZ_HXX);

        sTemplate = stgTemplateLoader.pick(STGTemplates.RPC_BIZ_WRITE_CXX_STG_NAME,
                STGTemplates.RPC_BIZ_WRITE_CXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createRPCWriteServiceFile(sourceData, sTemplate.render(), ContentType.RPC_SERVICE_BIZ_CXX);
        return null;
    }

    private void createRPCRegisterFile(RpcServiceRegister sourceData, String content, ContentType contentType) {
        String rpcRegisterFile = "autogen_" + sourceData.getComponentName() + "_register" + ".cpp";
        createRPCFile(westLakeHome, sourceData, rpcRegisterFile, content);
    }

    private void createRPCWriteServiceFile(RpcServiceRegister sourceData, String content, ContentType contentType) {
        if (contentType == ContentType.RPC_SERVICE_BIZ_HXX) {
            String rpcServiceWHxxFile = "write_" + sourceData.getComponentName() + "_service" + ".h";
            createRPCFile(westLakeHome, sourceData, rpcServiceWHxxFile, content);
        }
        if (contentType == ContentType.RPC_SERVICE_BIZ_CXX) {
            String rpcServiceWCxxFile = "write_" + sourceData.getComponentName() + "_service" + ".cpp";
            createRPCFile(westLakeHome, sourceData, rpcServiceWCxxFile, content);
        }
    }

    private void createRPCServiceFile(RpcServiceRegister sourceData, String content, ContentType contentType) {
        if (contentType == ContentType.RPC_SERVICE_HXX) {
            String rpcServiceHxxFile = "autogen_" + sourceData.getComponentName() + "_service" + ".h";
            createRPCFile(westLakeHome, sourceData, rpcServiceHxxFile, content);
        }
        if (contentType == ContentType.RPC_SERVICE_CXX) {
            String rpcServiceCxxFile = "autogen_" + sourceData.getComponentName() + "_service" + ".cpp";
            createRPCFile(westLakeHome, sourceData, rpcServiceCxxFile, content);
        }
    }

    private void createRPCFile(String westLakeHome, RpcServiceRegister sourceData, String fileName, String content) {
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("src").append(File.separator)
                .append(sourceData.getModuleName()).append(File.separator)
                .append("asf_mdbcom_")
                .append(sourceData.getComponentName()).append(File.separator)
                .append(fileName);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate RPC service file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }
}
