package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.generator.builder.FmErrorBuilder;
import com.cjhxfund.autocode.model.out.common.FmErrors;
import com.cjhxfund.autocode.wesklake.model.xsd.common.error.ArrayOfSystemErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class SystemErrorAssembler implements ContentFileAssembler<FmErrors, ArrayOfSystemErrorType> {

    @Autowired
    private FmErrorBuilder errorBuilder;

    @Override
    public FmErrors assemble(String subSystem, String moduleName, String destFileName,
                             ArrayOfSystemErrorType errorType) {

        return errorBuilder.build(errorType.getSystemError());

    }
}
