package com.cjhxfund.autocode.generator.mdb;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.out.mdb.DBTableImplCxx;
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
public class MDBTableCxxImplGenerator extends BaseContentFileGenerator implements PlatFileGenerator<DBTableImplCxx> {

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Override
    public ContentFile generate(DBTableImplCxx sourceData) {
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.MDB_TABLE_CXX_STG_NAME, STGTemplates.MDB_TABLE_CXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createMDBTableImplFile(sourceData, sTemplate.render());
        return null;
    }

    private void createMDBTableImplFile(DBTableImplCxx sourceData, String content) {
        String mdbTableFile = "mt_" + sourceData.getName() + ".cpp";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("src").append(File.separator)
                .append("bizcommon").append(File.separator)
                .append("rxmdb_").append(sourceData.getSubSystem()).append(File.separator)
                .append(mdbTableFile);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate MDB table impl file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

}
