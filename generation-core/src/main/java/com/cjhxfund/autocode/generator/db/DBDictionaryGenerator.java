package com.cjhxfund.autocode.generator.db;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.out.table.Dictionaries;
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
public class DBDictionaryGenerator extends BaseContentFileGenerator implements PlatFileGenerator<Dictionaries> {

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Override
    public ContentFile generate(Dictionaries sourceData) {
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.DB_DICTIONARY_STG_NAME,
                STGTemplates.DB_DICTIONARY_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createDictSqlFile(sTemplate.render());
        return null;
    }

    private void createDictSqlFile(String content) {
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("db").append(File.separator)
                .append("data").append(File.separator)
                .append("dict.sql");
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate dictionary sql file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }


}
