package com.cjhxfund.autocode.generator.builder;

import com.cjhxfund.autocode.cache.SystemErrorsRetriever;
import com.cjhxfund.autocode.model.out.common.FmErrors;
import com.cjhxfund.autocode.model.out.common.SystemError;
import com.cjhxfund.autocode.wesklake.model.xsd.common.error.SystemErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class FmErrorBuilder {

    private final static String SYSTEM_ERROR_NAMESPACE = "bizerror_id";

    @Autowired
    private SystemErrorsRetriever systemErrorsRetriever;

    public FmErrors build(List<SystemErrorType> systemErrorTypeList) {
        FmErrors fmErrors = FmErrors.builder().hashKey("#").sysNamespace(SYSTEM_ERROR_NAMESPACE).build();
        List<SystemErrorType> systemErrorTypes = new ArrayList<>();
        if (systemErrorTypeList == null || systemErrorTypeList.size() == 0) {
            systemErrorTypes = systemErrorsRetriever.findSystemErrors();
        } else {
            systemErrorTypes = systemErrorTypeList;
        }
        List<SystemError> systemErrors = new ArrayList<>();
        for (SystemErrorType sysError : systemErrorTypes) {
            systemErrors.add(SystemError
                    .builder()
                    .errorId(Integer.parseInt(sysError.getId()))
                    .errorName(sysError.getEnName())
                    .description(sysError.getDescribe().toUpperCase())
                    .build());
        }
        fmErrors.setErrors(systemErrors.toArray(new SystemError[systemErrors.size()]));
        return fmErrors;
    }

}
