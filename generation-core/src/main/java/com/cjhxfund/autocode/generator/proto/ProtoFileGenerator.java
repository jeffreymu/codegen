package com.cjhxfund.autocode.generator.proto;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.ContentType;
import com.cjhxfund.autocode.model.out.proto.ProtoFlatContent;
import com.cjhxfund.autocode.template.STGTemplates;
import com.cjhxfund.autocode.template.TemplateLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.stringtemplate.v4.ST;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class ProtoFileGenerator extends BaseContentFileGenerator implements PlatFileGenerator<ProtoFlatContent> {

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Value("${westlake.proto.shadow}")
    private String protoPath;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Override
    public ContentFile generate(ProtoFlatContent sourceData) {
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.PROTO_FILE_STG_NAME,
                STGTemplates.PROTO_FILE_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        try {
            createProtoFile(sourceData, sTemplate.render(), ContentType.PROTO_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * generate proto file of message methods
     * @param sourceData
     * @param content
     * @param contentType
     */
    private void createProtoFile(ProtoFlatContent sourceData, String content, ContentType contentType) throws IOException {
        if (contentType != ContentType.PROTO_FILE) {
            return;
        }
        String protoFile = sourceData.getModuleName() + ".proto";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("workspace").append(File.separator)
                .append("fm_proto").append(File.separator)
                .append(protoFile);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate RPC proto file {} ", targetDescFile);
        writeContent(targetDescFile, content);
        copyProtoFiles(targetDescFile, protoFile);
    }

    private void copyProtoFiles(String targetDescFile, String protoFile) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append(protoPath).append(File.separator)
                .append("proto-multi-gen").append(File.separator)
                .append("src").append(File.separator)
                .append("main").append(File.separator)
                .append("proto").append(File.separator);
        String protoShadowFile = builder.append(File.separator).append(protoFile).toString();
        String protoOutputDir = westLakeHome + File.separator + "protobuff";
        String protoOutputFile = protoOutputDir + File.separator + protoFile;
        log.info("Copy proto file {} ", protoShadowFile);
        log.info("Copy proto file {} ", protoOutputFile);
        copyFile(targetDescFile, protoShadowFile);
        copyFile(targetDescFile, protoOutputFile);
    }
}
