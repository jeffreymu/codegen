package com.cjhxfund.autocode.cache;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.ArrayOfDictType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.DictType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class SystemDictRetriever {

    @Autowired
    private DataCacheManager cacheManager;

    private Map<String, DictType> dictMap = new HashMap<>();

    public List<DictType> findSystemDicts() {
        ArrayOfDictType arrayOfDictType = (ArrayOfDictType) cacheManager.get(
                WestLakeSourceFileConfig.DICT_CACHE_KEY);
        for(DictType dict : arrayOfDictType.getDict()) {
            dictMap.put(dict.getId(), dict);
        }
        return arrayOfDictType.getDict();
    }

    public DictType findDictByID(String dictId) {
        return dictMap.get(dictId);
    }
}
