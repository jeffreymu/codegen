package com.cjhxfund.autocode.wesklake.loader;

import com.cjhxfund.autocode.cache.DataCacheManager;
import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.error.ArrayOfSystemErrorType;
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
public class BasicSystemErrorSourceFileLoader implements SourceDefFileLoader {

    @Autowired
    private DataCacheManager cache;

    @Value("${westlake.installation.path}")
    private String westLakeHome;

    @PostConstruct
    private void init() {
        load(WestLakeSourceFileConfig.SYS_ERROR_SOURCE_XML_FILE_NAME, ArrayOfSystemErrorType.class);
    }

    @Override
    public void load(String fileName, Class... clazz) {
        ArrayOfSystemErrorType arrayOfSystemError = null;
        String systemErrorFile = westLakeHome
                + WestLakeSourceFileConfig.COMMON_FILES_PATH
                + fileName;
        try {
            arrayOfSystemError = (ArrayOfSystemErrorType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.SYS_ERROR_CACHE_KEY).parse(new File(systemErrorFile), clazz[0]);
        } catch (JAXBException e) {
            e.printStackTrace();
            log.error("Parse {} file error", fileName);
        }
        if (arrayOfSystemError == null) {
            return;
        }
        log.info("Loaded {} system errors from {}", arrayOfSystemError.getSystemError().size(), systemErrorFile);
        cache.put(WestLakeSourceFileConfig.SYS_ERROR_CACHE_KEY, arrayOfSystemError);
    }
}
