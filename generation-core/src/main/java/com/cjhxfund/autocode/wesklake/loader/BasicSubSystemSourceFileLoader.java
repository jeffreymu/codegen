package com.cjhxfund.autocode.wesklake.loader;

import com.cjhxfund.autocode.cache.DataCacheManager;
import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.system.ArrayOfSubsystemType;
import com.cjhxfund.autocode.wesklake.parse.DefinitionFileParserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Slf4j
@Component
public class BasicSubSystemSourceFileLoader implements SourceDefFileLoader {

    @Autowired
    private DataCacheManager cache;

    @Value("${westlake.installation.path}")
    private String westLakeHome;

    @PostConstruct
    private void init() {
        load(WestLakeSourceFileConfig.SUB_SYSTEM_SOURCE_XML_FILE_NAME, ArrayOfSubsystemType.class);
    }

    @Override
    public void load(String fileName, Class... clazz) {
        ArrayOfSubsystemType arrayOfSubsystemType = null;
        String subSystemFile = westLakeHome
                + WestLakeSourceFileConfig.COMMON_FILES_PATH
                + fileName;
        try {
            arrayOfSubsystemType = (ArrayOfSubsystemType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.SUB_SYSTEM_CACHE_KEY).parse(new File(subSystemFile), clazz[0]);
        } catch (JAXBException e) {
            e.printStackTrace();
            log.error("Parse {} file error", fileName);
        }
        if (arrayOfSubsystemType == null) {
            return;
        }
        log.info("Loaded {} sub-systems from {}", arrayOfSubsystemType.getSubsystem().size(), subSystemFile);
        cache.put(WestLakeSourceFileConfig.SUB_SYSTEM_CACHE_KEY, arrayOfSubsystemType);
    }
}
