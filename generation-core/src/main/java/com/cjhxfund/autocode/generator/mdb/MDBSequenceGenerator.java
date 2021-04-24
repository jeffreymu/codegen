package com.cjhxfund.autocode.generator.mdb;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.out.mdb.MDBRecoverySequence;
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
public class MDBSequenceGenerator extends BaseContentFileGenerator implements PlatFileGenerator<MDBRecoverySequence> {

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Override
    public ContentFile generate(MDBRecoverySequence sourceData) {
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.SEQUENCE_RECOVERY_STG_NAME, STGTemplates.SEQUENCE_RECOVERY_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createSequenceRecoveryCppFile(sTemplate.render());
        return null;
    }

    private void createSequenceRecoveryCppFile(String content) {
        String sequenceRecovery = "sequence_recovery.cpp";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("src").append(File.separator)
                .append("bizcommon").append(File.separator)
                .append("invest_common").append(File.separator)
                .append(sequenceRecovery);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate sequence recovery file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }


}
