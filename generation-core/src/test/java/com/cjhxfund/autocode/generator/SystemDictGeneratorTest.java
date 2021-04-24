package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.model.out.common.DictEnum;
import com.cjhxfund.autocode.model.out.common.SystemDict;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.ArrayOfDictType;
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
public class SystemDictGeneratorTest {

    public static void generate(ArrayOfDictType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);

        List<DictEnum> enums = new ArrayList<>();
        enums.add(DictEnum.builder()
                .enumValue("ZHENGCHANGDAOZHANG")
                .orderNo(1)
                .description("正常到账")
                .build());
        enums.add(DictEnum.builder()
                .enumValue("ZHIYA")
                .orderNo(2)
                .description("质押")
                .build());
        sTemplate.add("params", SystemDict.builder()
                .hashKey("#")
                .namespaceLevel1("invest")
                .namespaceLevel2("dict")
                .namespaceLevel3("abnormal_cause")
                .name("abnormal_cause")
                .upperName("abnormal_cause".toUpperCase())
                .lastEnum(DictEnum.builder()
                        .enumValue("JITA")
                        .orderNo(5)
                        .description("其它")
                        .build())
                .enums(enums.toArray(new DictEnum[enums.size()]))
                .build());
        log.info(sTemplate.render());
    }


    public static void main(String[] args) throws JAXBException {
        ArrayOfDictType result = null;
        //SystemDictParser.parse("dict.xml", ArrayOfDictType.class);
        SystemDictGeneratorTest.generate(result, "st/system/system-dict.stg", "dictTemplate");

    }
}
