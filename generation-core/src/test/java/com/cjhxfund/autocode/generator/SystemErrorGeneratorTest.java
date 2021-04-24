package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.model.out.common.FmErrors;
import com.cjhxfund.autocode.model.out.common.SystemError;
import com.cjhxfund.autocode.wesklake.model.xsd.common.error.ArrayOfSystemErrorType;
import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/14.
 */
@Slf4j
public class SystemErrorGeneratorTest {

    public static void generate(ArrayOfSystemErrorType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);

        List<SystemError> errors = new ArrayList<>();
        errors.add(SystemError.builder()
                .errorId(10000)
                .errorName("SECURITY_NOT_FOUND")
                .description("找不到证券资料")
                .build());
        errors.add(SystemError.builder()
                .errorId(10001)
                .errorName("INST_NOT_FOUND")
                .description("找不到机构信息")
                .build());
        FmErrors fmErrors = FmErrors.builder()
                .hashKey("#")
                .sysNamespace("bizerror_id")
                .errors(errors.toArray(new SystemError[errors.size()]))
                .build();

        sTemplate.add("errors", fmErrors);
        log.info(sTemplate.render());
    }


    public static void main(String[] args) throws JAXBException {
        ArrayOfSystemErrorType result = null;
//        SystemErrorParser.parse(
//                "system_error.xml", ArrayOfSystemErrorType.class);
        SystemErrorGeneratorTest.generate(result, "st/system/system-error.stg", "errorTemplate");

    }
}
