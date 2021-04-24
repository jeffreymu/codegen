package com.cjhxfund.autocode.generator.builder;

import com.cjhxfund.autocode.cache.SystemDictRetriever;
import com.cjhxfund.autocode.model.out.common.DictEnum;
import com.cjhxfund.autocode.model.out.common.SystemDict;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.DictItemType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.DictType;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public final class SystemDictBuilder {

    @Autowired
    private SystemDictRetriever systemDictRetriever;

    public SystemDict build(String dictId) {
        SystemDict systemDict = SystemDict.builder()
                .hashKey("#")
                .namespaceLevel1("invest")
                .namespaceLevel2("dict")
                .build();
        DictType dictType = systemDictRetriever.findDictByID(dictId);
        buildDict(systemDict, dictType);
        return systemDict;
    }

    public SystemDict build(DictType dictType) {
        SystemDict systemDict = SystemDict.builder()
                .hashKey("#")
                .namespaceLevel1("invest")
                .namespaceLevel2("dict")
                .build();
        buildDict(systemDict, dictType);
        return systemDict;
    }

    private void buildDict(SystemDict systemDict, DictType dictType) {
        if (dictType != null) {
            if (dictType.getEnName() == null || dictType.getEnName().equals("")) {
                log.warn("Dict enName is empty for {}", dictType.getId());
                return;
            }
            systemDict.setName(dictType.getEnName());
            systemDict.setCnName(dictType.getName());
            systemDict.setUpperName(dictType.getEnName().toUpperCase());
            systemDict.setNamespaceLevel3(dictType.getEnName());
            List<DictEnum> enums = new ArrayList<>();
            DictEnum lastEnum = DictEnum.builder().build();
            List<DictItemType> itemTypes = dictType.getItems().getDictItem();
            int maxItemSize = itemTypes.size();
            for (int i = 0; i < itemTypes.size() - 1; i++) {
                enums.add(DictEnum.builder()
                        .orderNo(Integer.parseInt(itemTypes.get(i).getId()))
                        .description(itemTypes.get(i).getName())
                        .enumValue(pinyin(itemTypes.get(i).getName()))
                        .build());
            }
            systemDict.setEnums(enums.toArray(new DictEnum[enums.size()]));
            lastEnum.setOrderNo(Integer.parseInt(itemTypes.get(maxItemSize - 1).getId()));
            lastEnum.setEnumValue(pinyin(itemTypes.get(maxItemSize - 1).getName()));
            lastEnum.setDescription(itemTypes.get(maxItemSize - 1).getName());
            systemDict.setLastEnum(lastEnum);
        }
    }
    private String pinyin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] input = inputString.trim().toCharArray();
        String chinesePinYinOutput = "";
        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    chinesePinYinOutput += temp[0];
                } else
                    chinesePinYinOutput += java.lang.Character.toString(input[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return chinesePinYinOutput;
    }

}
