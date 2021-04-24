package com.cjhxfund.autocode.template;

import com.cjhxfund.autocode.model.ContentFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeffrey on 2021/3/18.
 */
@Slf4j
@Component
public class StgTemplateLoader implements TemplateLoader<ContentFile> {

    private final static String STG_TEMPLATE_FILE_EXT = "stg";
    private final static String STG_TEMPLATE_RESOURCE_PATH = "st";

    private Map<String, TemplateDefinition> stgTemplates = new HashMap<>();

    @PostConstruct
    private void loadStgTemplate() {
        String path = Class.class.getClass().getResource("/").getPath();
        List<File> fileList = (List<File>) FileUtils.listFiles(
                new File(path + File.separator + STG_TEMPLATE_RESOURCE_PATH),
                new String[]{STG_TEMPLATE_FILE_EXT}, true);
        if (!fileList.isEmpty()) {
            for (File f : fileList) {
                STGroup stg = new STGroupFile(f.getPath());
                stgTemplates.put(f.getName(), TemplateDefinition.builder()
                        .stgFileName(f.getName())
                        .stGroup(stg)
                        .build());
            }

            log.info("++++++++++++++++++++++++++++++++++++");
            log.info("Loaded {} STG templates", stgTemplates.values().size());
            stgTemplates.forEach((key, value) -> log.info(key + "#" + value));
            log.info("++++++++++++++++++++++++++++++++++++");
        }
    }

    private TemplateDefinition find(String stgFile) {
        return stgTemplates.get(stgFile);
    }

    public ST pick(String stgFileName, String templateName) {
        TemplateDefinition templateDef = find(stgFileName);
        if(templateDef == null) {
            throw new IllegalArgumentException("Must provide a correct stg template name");
        }
        ST sTemplate = templateDef.getStGroup().getInstanceOf(templateName);
        if (sTemplate == null) {
            throw new RuntimeException("Can't load ST template " + templateName);
        }
        return sTemplate;
    }

    @Override
    public String render(ST sTemplate, String paramName, ContentFile contentFile) {
        if (sTemplate == null) {
            log.error("Empty STG template {}", sTemplate);
        }
        sTemplate.add(paramName, contentFile);
        return sTemplate.render();
    }

}
