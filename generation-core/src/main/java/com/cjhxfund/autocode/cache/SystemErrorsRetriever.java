package com.cjhxfund.autocode.cache;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.error.ArrayOfSystemErrorType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.error.SystemErrorType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.ArrayOfStdfieldType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.ArrayOfStdtypeType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.StdfieldType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.StdtypeType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.system.ArrayOfSubsystemType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.system.SubsystemType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class SystemErrorsRetriever {

    @Autowired
    private DataCacheManager cacheManager;

    public List<SystemErrorType> findSystemErrors() {
        ArrayOfSystemErrorType arrayOfSystemErrorType = (ArrayOfSystemErrorType) cacheManager.get(
                WestLakeSourceFileConfig.SYS_ERROR_CACHE_KEY);
        return arrayOfSystemErrorType.getSystemError();
    }

}
