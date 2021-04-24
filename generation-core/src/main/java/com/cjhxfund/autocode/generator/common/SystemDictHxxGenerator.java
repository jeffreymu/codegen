package com.cjhxfund.autocode.generator.common;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.out.common.SystemDict;
import com.cjhxfund.autocode.model.out.common.SystemDictHxx;
import com.cjhxfund.autocode.template.STGTemplates;
import com.cjhxfund.autocode.template.TemplateLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.stringtemplate.v4.ST;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class SystemDictHxxGenerator extends BaseContentFileGenerator implements PlatFileGenerator<SystemDictHxx> {

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Override
    public ContentFile generate(SystemDictHxx sourceData) {
        List<SystemDict> systemDicts = new ArrayList<>(Arrays.asList(sourceData.getDicts()));
        for (SystemDict systemDict : systemDicts) {
            ST sTemplate = stgTemplateLoader.pick(STGTemplates.SYSTEM_DICT_STG_NAME, STGTemplates.SYSTEM_DICT_STG_TEMPLATE_NAME);
            sTemplate.add("params", systemDict);
            String content = sTemplate.render();
            String dictHeaderFile = systemDict.getName() + ".h";
            StringBuilder errorDescPath = new StringBuilder();
            errorDescPath.append(westLakeHome).append(File.separator)
                    .append("server").append(File.separator)
                    .append("include").append(File.separator)
                    .append("dictionary").append(File.separator)
                    .append(dictHeaderFile);
            String targetDescFile = errorDescPath.toString();
            log.info("Generate system dict header file {} ", targetDescFile);
            writeContent(targetDescFile, content);
        }
        return null;
    }
}
