package com.cjhxfund.autocode.cache;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.ArrayOfSequenceType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.SequenceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class DatabaseSequenceRetriever {

    @Autowired
    private DataCacheManager cacheManager;

    public List<SequenceType> findSequences() {
        ArrayOfSequenceType arrayOfSequenceType = (ArrayOfSequenceType) cacheManager.get(
                WestLakeSourceFileConfig.DB_TABLE_SEQUENCE_CACHE_KEY);
        return arrayOfSequenceType.getSequence();
    }

}
