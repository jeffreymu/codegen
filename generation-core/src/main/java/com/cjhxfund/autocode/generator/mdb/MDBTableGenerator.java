package com.cjhxfund.autocode.generator.mdb;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.out.mdb.MDBTable;
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
public class MDBTableGenerator extends BaseContentFileGenerator implements PlatFileGenerator<MDBTable>  {

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Override
    public ContentFile generate(MDBTable sourceData) {
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.MRTTI_HXX_STG_NAME, STGTemplates.MRTTI_HXX_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        String content = sTemplate.render();
        createRTTIHeaderFile(sourceData, content);

//        sTemplate = stgTemplateLoader.pick(STGTemplates.MDB_RTTI_IMPL_CXX_STG_NAME, STGTemplates.MDB_RTTI_IMPL_CXX_STG_TEMPLATE_NAME);
//        sTemplate.add("params", sourceData);
//        createRTTIImplFile(sourceData, sTemplate.render());
//
//        sTemplate = stgTemplateLoader.pick(STGTemplates.MDB_TABLE_ENUM_STG_NAME, STGTemplates.MDB_TABLE_ENUM_STG_TEMPLATE_NAME);
//        sTemplate.add("params", sourceData);
//        createMDBEnumHeaderFile(sourceData, sTemplate.render());

        return null;
    }

    private void createMDBEnumHeaderFile(MDBTable sourceData, String content) {
        String mdbTableEnumFile = "mtableenum_" + sourceData.getModule() + ".h";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("include").append(File.separator)
                .append("rxmdb_").append(sourceData.getModule()).append(File.separator)
                .append(mdbTableEnumFile);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate MDB table enum header file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

    private void createRTTIHeaderFile(MDBTable sourceData, String content) {
        String mrttiFileName = "mrtti_" + sourceData.getModule() + ".h";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("include").append(File.separator)
                .append("rxmdb_").append(sourceData.getModule()).append(File.separator)
                .append(mrttiFileName);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate RTTI header file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

    private void createRTTIImplFile(MDBTable sourceData, String content) {
        String mrttiFileName = "mrtti_" + sourceData.getModule() + ".cpp";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("server").append(File.separator)
                .append("src").append(File.separator)
                .append("bizcommon").append(File.separator)
                .append("rxmdb_").append(sourceData.getModule()).append(File.separator)
                .append(mrttiFileName);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate RTTI impl file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

}
