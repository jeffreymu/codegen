package com.cjhxfund.autocode.wesklake.loader;

import com.cjhxfund.autocode.cache.DataCacheManager;
import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.ArrayOfStdfieldType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.ArrayOfStdtypeType;
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
public class BasicStdTypeSourceFileLoader implements SourceDefFileLoader {

    @Autowired
    private DataCacheManager cache;

    @Value("${westlake.installation.path}")
    private String westLakeHome;

    @PostConstruct
    private void init() {
        load(WestLakeSourceFileConfig.STD_TYPE_SOURCE_XML_FILE_NAME,
                ArrayOfStdtypeType.class, ArrayOfStdfieldType.class);
    }

    @Override
    public void load(String fileName, Class... clazz) {
        ArrayOfStdtypeType arrayOfStdtypeType = null;
        ArrayOfStdfieldType arrayOfStdfieldType = null;
        String stdTypeFile = westLakeHome
                + WestLakeSourceFileConfig.COMMON_FILES_PATH
                + WestLakeSourceFileConfig.STD_TYPE_SOURCE_XML_FILE_NAME;
        String stdFieldFile = westLakeHome
                + WestLakeSourceFileConfig.COMMON_FILES_PATH
                + WestLakeSourceFileConfig.STD_FIELD_SOURCE_XML_FILE_NAME;
        try {
            arrayOfStdtypeType = (ArrayOfStdtypeType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.STD_TYPE_CACHE_KEY).parse(new File(stdTypeFile), clazz[0]);
            arrayOfStdfieldType = (ArrayOfStdfieldType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.STD_FIELD_CACHE_KEY).parse(new File(stdFieldFile), clazz[1]);
        } catch (JAXBException e) {
            e.printStackTrace();
            log.error("Parse {} file error", fileName);
        }

        if (arrayOfStdtypeType != null) {
            log.info("Loaded {} system types from {}", arrayOfStdtypeType.getStdtype().size(), stdTypeFile);
            cache.put(WestLakeSourceFileConfig.STD_TYPE_CACHE_KEY, arrayOfStdtypeType);
        }
        if (arrayOfStdfieldType != null) {
            log.info("Loaded {} system fields from {}", arrayOfStdfieldType.getStdfield().size(), stdFieldFile);
            cache.put(WestLakeSourceFileConfig.STD_FIELD_CACHE_KEY, arrayOfStdfieldType);
        }
    }
}
