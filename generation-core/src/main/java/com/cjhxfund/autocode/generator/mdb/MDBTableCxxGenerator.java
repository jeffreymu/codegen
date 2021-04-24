package com.cjhxfund.autocode.generator.mdb;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.out.mdb.DBTableCxx;
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
public class MDBTableCxxGenerator extends BaseContentFileGenerator implements PlatFileGenerator<DBTableCxx> {

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Override
    public ContentFile generate(DBTableCxx sourceData) {
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.MDB_TABLE_HXX_STG_NAME, STGTemplates.MDB_TABLE_HXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createMTHeaderFile(sourceData, sTemplate.render());

        sTemplate = stgTemplateLoader.pick(STGTemplates.MDB_RTTI_IMPL_CXX_STG_NAME, STGTemplates.MDB_RTTI_IMPL_CXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createRTTIImplFile(sourceData, sTemplate.render());

        sTemplate = stgTemplateLoader.pick(STGTemplates.MDB_TABLE_ENUM_STG_NAME, STGTemplates.MDB_TABLE_ENUM_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createMDBEnumHeaderFile(sourceData, sTemplate.render());

        sTemplate = stgTemplateLoader.pick(STGTemplates.MDB_BIZ_HXX_STG_NAME, STGTemplates.MDB_BIZ_HXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createMDBModuleHeaderFile(sourceData, sTemplate.render());

        sTemplate = stgTemplateLoader.pick(STGTemplates.MDB_BIZ_IMPL_HXX_STG_NAME, STGTemplates.MDB_BIZ_IMPL_HXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createMDBModuleImplHeaderFile(sourceData, sTemplate.render());

        sTemplate = stgTemplateLoader.pick(STGTemplates.MDB_BIZ_IMPL_CXX_STG_NAME, STGTemplates.MDB_BIZ_IMPL_CXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createMDBModuleImplFile(sourceData, sTemplate.render());

        return null;
    }

    private void createMDBModuleImplFile(DBTableCxx sourceData, String content) {
        String mdbTableModuleFile = "mdb_" + sourceData.getSubSystem() + "_impl.cpp";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("src").append(File.separator)
                .append("bizcommon").append(File.separator)
                .append("rxmdb_").append(sourceData.getSubSystem()).append(File.separator)
                .append(mdbTableModuleFile);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate MDB table module impl file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

    private void createMDBModuleImplHeaderFile(DBTableCxx sourceData, String content) {
        String mdbTableModuleFile = "mdb_" + sourceData.getSubSystem() + "_impl.h";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("include").append(File.separator)
                .append("rxmdb_").append(sourceData.getSubSystem()).append(File.separator)
                .append(mdbTableModuleFile);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate MDB table module impl file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

    private void createMDBModuleHeaderFile(DBTableCxx sourceData, String content) {
        String mdbTableModuleFile = "mdb_" + sourceData.getSubSystem() + ".h";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("include").append(File.separator)
                .append("rxmdb_").append(sourceData.getSubSystem()).append(File.separator)
                .append(mdbTableModuleFile);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate MDB table module header file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

    private void createMDBEnumHeaderFile(DBTableCxx sourceData, String content) {
        String mdbTableEnumFile = "mtableenum_" + sourceData.getSubSystem() + ".h";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("include").append(File.separator)
                .append("rxmdb_").append(sourceData.getSubSystem()).append(File.separator)
                .append(mdbTableEnumFile);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate MDB table enum header file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

    private void createRTTIImplFile(DBTableCxx sourceData, String content) {
        String mrttiFileName = "mrtti_" + sourceData.getSubSystem() + ".cpp";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("src").append(File.separator)
                .append("bizcommon").append(File.separator)
                .append("rxmdb_").append(sourceData.getSubSystem()).append(File.separator)
                .append(mrttiFileName);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate RTTI impl file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

    private void createMTHeaderFile(DBTableCxx sourceData, String content) {
        String mdbTableHeaderFile = "mt_" + sourceData.getSubSystem() + ".h";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("include").append(File.separator)
                .append("rxmdb_").append(sourceData.getSubSystem()).append(File.separator)
                .append(mdbTableHeaderFile);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate MDB table header header file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

}
