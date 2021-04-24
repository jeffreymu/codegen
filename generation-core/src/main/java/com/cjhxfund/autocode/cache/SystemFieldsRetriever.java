package com.cjhxfund.autocode.cache;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
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
public class SystemFieldsRetriever {

    @Autowired
    private DataCacheManager cacheManager;

    public SubsystemType findSubSystem(String systemID) {
        ArrayOfSubsystemType arrayOfSubsystemType = (ArrayOfSubsystemType) cacheManager.get(
                WestLakeSourceFileConfig.SUB_SYSTEM_CACHE_KEY);
        List<SubsystemType> subsystemTypes = arrayOfSubsystemType.getSubsystem();
        for (SubsystemType type : subsystemTypes) {
            if (type.getId().equals(systemID)) {
                return type;
            }
        }
        return null;
    }

    public StdtypeType findField(String fieldName) {
        ArrayOfStdfieldType arrayOfStdfieldType = (ArrayOfStdfieldType) cacheManager.get(
                WestLakeSourceFileConfig.STD_FIELD_CACHE_KEY);
        List<StdfieldType> stdfieldTypes = arrayOfStdfieldType.getStdfield();
        StdtypeType stdtypeType = null;
        for (StdfieldType field : stdfieldTypes) {
            if (field.getEnName().equals(fieldName)) {
                stdtypeType = findFieldType(field.getStdTypeName());
                break;
            }
        }

        if (stdtypeType == null)
            throw new IllegalArgumentException("Illegal field name " + fieldName);
        return stdtypeType;
    }

    public StdfieldType findFieldTypeInfo(String fieldName) {
        ArrayOfStdfieldType arrayOfStdfieldType = (ArrayOfStdfieldType) cacheManager.get(
                WestLakeSourceFileConfig.STD_FIELD_CACHE_KEY);
        List<StdfieldType> stdfieldTypes = arrayOfStdfieldType.getStdfield();
        for (StdfieldType field : stdfieldTypes) {
            if (field.getEnName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    public StdtypeType findFieldType(String typeName) {
        ArrayOfStdtypeType arrayOfStdtypeType = (ArrayOfStdtypeType) cacheManager.get(
                WestLakeSourceFileConfig.STD_TYPE_CACHE_KEY);
        List<StdtypeType> stdTypes = arrayOfStdtypeType.getStdtype();
        for (StdtypeType type : stdTypes) {
            if (type.getName().equals(typeName)) {
                return type;
            }
        }
        return null;
    }

}
