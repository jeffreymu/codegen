package com.cjhxfund.autocode.wesklake.loader;

import com.cjhxfund.autocode.cache.DataCacheManager;
import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.ArrayOfSequenceType;
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
public class DBSequenceFileLoader implements SourceDefFileLoader {

    @Value("${westlake.installation.path}")
    private String westLakeHome;

    @Autowired
    private DataCacheManager cache;

    @PostConstruct
    private void init() {
        load(WestLakeSourceFileConfig.DB_TABLE_SEQUENCE_XML_FILE_NAME,
                ArrayOfSequenceType.class);
    }

    @Override
    public void load(String fileName, Class... clazz) {
        ArrayOfSequenceType arrayOfSequenceType = null;
        String sequenceFile = westLakeHome
                + WestLakeSourceFileConfig.COMMON_FILES_PATH
                + fileName;
        try {
            arrayOfSequenceType = (ArrayOfSequenceType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.DB_TABLE_SEQUENCE_CACHE_KEY).parse(new File(sequenceFile), clazz[0]);
        } catch (JAXBException e) {
            e.printStackTrace();
            log.error("Parse {} file error", fileName);
        }
        if (arrayOfSequenceType == null) {
            return;
        }
        log.info("Loaded {} database sequences from {}", arrayOfSequenceType.getSequence().size(), sequenceFile);
        cache.put(WestLakeSourceFileConfig.DB_TABLE_SEQUENCE_CACHE_KEY, arrayOfSequenceType);
    }
}
