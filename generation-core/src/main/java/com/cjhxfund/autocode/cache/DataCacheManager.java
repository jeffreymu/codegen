package com.cjhxfund.autocode.cache;

/**
 * Created by Jeffrey on 2021/3/17.
 */
public interface DataCacheManager {

    /**
     * put data into cache
     * @param key
     * @param object
     */
    void put(String key, Object object);

    /**
     * find cached object by key
     * @param key
     * @return
     */
    Object get(String key);

}
