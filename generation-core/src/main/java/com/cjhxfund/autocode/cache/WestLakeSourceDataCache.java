package com.cjhxfund.autocode.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Slf4j
@Component
public class WestLakeSourceDataCache implements DataCacheManager {

    private static int initialCapacity = 2048;
    private static long maxSize = 5000;
    private static long expireDurationOfHour = 24;

    private Cache<String, Object> cache;

    @PostConstruct
    private void init() {
        cache = Caffeine.newBuilder()
                .initialCapacity(initialCapacity)
                .maximumSize(maxSize)
                .expireAfterWrite(expireDurationOfHour, TimeUnit.HOURS)
                .build();
    }

    @Override
    public void put(String key, Object object) {
        cache.put(key, object);
    }

    @Override
    public Object get(String key) {
        return cache.getIfPresent(key);
    }

}
