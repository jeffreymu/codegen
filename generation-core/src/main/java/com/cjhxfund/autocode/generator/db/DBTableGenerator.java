package com.cjhxfund.autocode.generator.db;

import com.cjhxfund.autocode.generator.BaseContentFileGenerator;
import com.cjhxfund.autocode.generator.PlatFileGenerator;
import com.cjhxfund.autocode.generator.builder.TableXmlDomBuilder;
import com.cjhxfund.autocode.generator.wirter.XmlFileWriter;
import com.cjhxfund.autocode.model.ContentFile;
import com.cjhxfund.autocode.model.out.table.DBTable;
import com.cjhxfund.autocode.template.STGTemplates;
import com.cjhxfund.autocode.template.TemplateLoader;
import com.cjhxfund.autocode.wesklake.model.xsd.mysql.TableType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.stringtemplate.v4.ST;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class DBTableGenerator extends BaseContentFileGenerator implements PlatFileGenerator<DBTable> {

    private final static String HISTORY_TABLE_NAME_PREFIX = "this";
    private final static String TEMP_HISTORY_TABLE_NAME_PREFIX = "zhis";

    @Value("${westlake.code.path}")
    private String westLakeHome;

    @Autowired
    private TemplateLoader stgTemplateLoader;

    @Autowired
    private TableXmlDomBuilder tableXmlDomBuilder;

    @Override
    public ContentFile generate(DBTable dbTable) {
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.DB_TABLE_STG_NAME, STGTemplates.DB_TABLE_STG_TEMPLATE_NAME);
        sTemplate.add("params", dbTable);
        String content = sTemplate.render();
        String tableSqlFile = "autogen_" + dbTable.getTableFileName() + ".sql";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("db").append(File.separator)
                .append("table").append(File.separator)
                .append(dbTable.getTableModuleName()).append(File.separator)
                .append(tableSqlFile);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate SQL file {} ", targetDescFile);
        writeContent(targetDescFile, content);

        if (dbTable.isHasHisTable()) {
            generateHisTable(dbTable);
        }
        // generate mysql xml file
        try {
            generateMysqlFile(dbTable);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * generate MySQL XML files
     * @param dbTable
     * @throws JAXBException
     * @throws IOException
     */
    private void generateMysqlFile(DBTable dbTable) throws JAXBException, IOException {
        TableType tableType = tableXmlDomBuilder.createTableType(dbTable);
        String mysqlXmlFile = "autogen_" + dbTable.getName() + ".xml";
        String mysqlHisXmlFile = "autogen_" + dbTable.getHisName() + ".xml";
        if (tableType != null) {
            StringBuilder mysqlXmlDescPath = new StringBuilder();
            mysqlXmlDescPath.append(westLakeHome).append(File.separator)
                    .append("db").append(File.separator)
                    .append("mysql").append(File.separator)
                    .append("20-table").append(File.separator)
                    .append(mysqlXmlFile);
            String targetDescFile = mysqlXmlDescPath.toString();
            log.info("Generate MySQL file {} ", targetDescFile);
            XmlFileWriter.write(tableType, targetDescFile);

            if (dbTable.isHasHisTable()) {
                StringBuilder mysqlHisXmlDescPath = new StringBuilder();
                mysqlHisXmlDescPath.append(westLakeHome).append(File.separator)
                        .append("db").append(File.separator)
                        .append("mysql").append(File.separator)
                        .append("30-table").append(File.separator)
                        .append(mysqlHisXmlFile);
                String hisTargetDescFile = mysqlHisXmlDescPath.toString();
                log.info("Generate MySQL history file {} ", hisTargetDescFile);
                XmlFileWriter.write(tableType, hisTargetDescFile);
            }
        }
    }

    /**
     * generate history table
     * @param dbTable
     */
    private void generateHisTable(DBTable dbTable) {
        if (!dbTable.isHasHisTable()) {
            log.warn("Ignore to generate history table {}", dbTable.getTableFileName());
            return;
        }
        String origTableName = dbTable.getName();
        dbTable.setHisName(HISTORY_TABLE_NAME_PREFIX + origTableName.substring(1));
        dbTable.setHisShadowName(TEMP_HISTORY_TABLE_NAME_PREFIX + origTableName.substring(1));
        ST sTemplate = stgTemplateLoader.pick(STGTemplates.DB_HISTORY_TABLE_STG_NAME, STGTemplates.DB_HISTORY_TABLE_STG_TEMPLATE_NAME);
        sTemplate.add("params", dbTable);
        String content = sTemplate.render();
        String tableSqlFile = "autogen_" + dbTable.getTableFileName() + ".sql";
        StringBuilder sqlDescPath = new StringBuilder();
        sqlDescPath.append(westLakeHome).append(File.separator)
                .append("db").append(File.separator)
                .append("histable").append(File.separator)
                .append(dbTable.getTableModuleName()).append(File.separator)
                .append(tableSqlFile);
        String targetDescFile = sqlDescPath.toString();
        log.info("Generate History SQL file {} ", targetDescFile);
        writeContent(targetDescFile, content);
    }

}
