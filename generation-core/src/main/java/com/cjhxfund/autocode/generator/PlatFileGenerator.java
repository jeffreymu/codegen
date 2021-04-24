package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.model.ContentFile;

/**
 * Created by Jeffrey on 2021/3/20.
 */
public interface PlatFileGenerator<T> {

    /**
     * generate content of plat file
     * @param sourceData
     * @return
     */
    ContentFile generate(T sourceData);
}
