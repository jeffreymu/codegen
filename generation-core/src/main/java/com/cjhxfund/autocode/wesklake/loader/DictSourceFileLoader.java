package com.cjhxfund.autocode.wesklake.loader;

import com.cjhxfund.autocode.cache.DataCacheManager;
import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.ArrayOfDictType;
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
public class DictSourceFileLoader implements SourceDefFileLoader {

    @Autowired
    private DataCacheManager cache;

    @Value("${westlake.installation.path}")
    private String westLakeHome;

    @PostConstruct
    private void init() {
        load(WestLakeSourceFileConfig.DICT_SOURCE_XML_FILE_NAME,
                ArrayOfDictType.class);
    }

    @Override
    public void load(String fileName, Class... clazz) {
        ArrayOfDictType arrayOfDictType = null;
        String dictFile = westLakeHome
                + WestLakeSourceFileConfig.COMMON_FILES_PATH
                + fileName;
        try {
            arrayOfDictType = (ArrayOfDictType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.DICT_CACHE_KEY).parse(new File(dictFile), clazz[0]);
        } catch (JAXBException e) {
            e.printStackTrace();
            log.error("Parse {} file error", fileName);
        }
        if (arrayOfDictType == null) {
            return;
        }
        log.info("Loaded {} system dicts from {}", arrayOfDictType.getDict().size(), dictFile);
        cache.put(WestLakeSourceFileConfig.DICT_CACHE_KEY, arrayOfDictType);
    }
}
