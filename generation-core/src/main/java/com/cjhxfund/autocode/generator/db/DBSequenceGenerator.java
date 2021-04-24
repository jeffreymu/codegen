package com.cjhxfund.autocode.generator.db;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.out.table.Sequences;
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
public class DBSequenceGenerator extends BaseContentFileGenerator implements PlatFileGenerator<Sequences> {

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Override
    public ContentFile generate(Sequences sourceData) {
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.DB_SEQUENCE_STG_NAME,
                STGTemplates.DB_SEQUENCE_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        String content = sTemplate.render();
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("db").append(File.separator)
                .append("table").append(File.separator)
                .append("sequence.sql");
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate sequence SQL file {} ", targetDescFile);
        writeContent(targetDescFile, content);
        return null;
    }
}
