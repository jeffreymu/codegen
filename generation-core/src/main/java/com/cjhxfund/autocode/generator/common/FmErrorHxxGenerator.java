package com.cjhxfund.autocode.generator.common;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.out.common.FmErrors;
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
public class FmErrorHxxGenerator extends BaseContentFileGenerator implements PlatFileGenerator<FmErrors> {

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Override
    public ContentFile generate(FmErrors sourceData) {
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.SYSTEM_ERROR_STG_NAME, STGTemplates.SYSTEM_ERROR_STG_TEMPLATE_NAME);
        sTemplate.add("errors", sourceData);
        String content = sTemplate.render();
        String fmErrorHeaderFile = "fm_error.h";
        StringBuilder errorDescPath = new StringBuilder();
        errorDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("include").append(File.separator)
                .append(fmErrorHeaderFile);
        String targetDescFile = errorDescPath.toString();
        log.info("Generate fm-error header file {} ", targetDescFile);
        writeContent(targetDescFile, content);
        return null;
    }


}
