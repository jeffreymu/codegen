package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.generator.builder.SystemDictBuilder;
import com.cjhxfund.autocode.model.out.common.SystemDict;
import com.cjhxfund.autocode.model.out.common.SystemDictHxx;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.DictType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class SystemDictHxxAssembler implements ContentFileAssembler<SystemDictHxx, List<DictType>> {

    @Autowired
    private SystemDictBuilder systemDictBuilder;

    @Override
    public SystemDictHxx assemble(String subSystem, String moduleName, String destFileName, List<DictType> dictTypes) {
        List<SystemDict> dictList = new ArrayList<>();
        for (DictType dict : dictTypes) {
            dictList.add(systemDictBuilder.build(dict));
        }
        return SystemDictHxx.builder()
                .hashKey("#")
                .dicts(dictList.toArray(new SystemDict[dictList.size()]))
                .build();
    }
}
