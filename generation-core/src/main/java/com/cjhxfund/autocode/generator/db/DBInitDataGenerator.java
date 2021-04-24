package com.cjhxfund.autocode.generator.db;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.generator.builder.InitDataXmlDomBuilder;
import com.cjhxfund.autocode.generator.wirter.XmlFileWriter;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.out.table.DBInitData;
import com.cjhxfund.autocode.template.STGTemplates;
import com.cjhxfund.autocode.template.TemplateLoader;
import com.cjhxfund.autocode.wesklake.model.xsd.mysql.init.DataType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.stringtemplate.v4.ST;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class DBInitDataGenerator extends BaseContentFileGenerator implements PlatFileGenerator<DBInitData> {

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Autowired
    private InitDataXmlDomBuilder initDataXmlDomBuilder;

    @Override
    public ContentFile generate(DBInitData sourceData) {
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.DB_INIT_DATA_STG_NAME,
                STGTemplates.DB_INIT_DATA_STG_TEMPLATE_NAME);
        sTemplate.add("params", sourceData);
        createDictSqlFile(sourceData, sTemplate.render());
        try {
            generateMysqlXmlFile(sourceData);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createDictSqlFile(DBInitData sourceData, String content) {
        if (sourceData == null) {
            log.warn("Empty database init data content");
            return;
        }
        String initDataSqlFile = "autogen_" + sourceData.getTableName() + ".sql";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("db").append(File.separator)
                .append("data").append(File.separator)
                .append(initDataSqlFile);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate dictionary sql file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

    private void generateMysqlXmlFile(DBInitData sourceData) throws JAXBException, IOException {
        DataType dataType = initDataXmlDomBuilder.createInitDataType(sourceData);
        String mysqlXmlFile = "autogen_" + sourceData.getTableName() + ".xml";
        if (dataType != null) {
            StringBuilder mysqlXmlDescPath = new StringBuilder();
            mysqlXmlDescPath.append(westLakeHome).append(File.separator)
                    .append("db").append(File.separator)
                    .append("mysql").append(File.separator)
                    .append("40-table").append(File.separator)
                    .append(mysqlXmlFile);
            String targetDescFile = mysqlXmlDescPath.toString();
            log.info("Generate MySQL file {} ", targetDescFile);
            XmlFileWriter.writeData(dataType, targetDescFile);
        }
    }
}
