package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.model.out.table.DBDictionary;
import com.cjhxfund.autocode.model.out.table.Dictionaries;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.ArrayOfDictType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.DictItemType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.DictType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class SystemDictSqlAssembler implements ContentFileAssembler<Dictionaries, ArrayOfDictType> {

    @Override
    public Dictionaries assemble(String subSystem, String moduleName, String destFileName, ArrayOfDictType dictType) {
        List<DBDictionary> dbDicts = new ArrayList<>();
        List<DictType> dictTypeList = dictType.getDict();
        for (DictType dict : dictTypeList) {
            DBDictionary dictionary = DBDictionary
                    .builder()
                    .dictId(dict.getId())
                    .dictName(dict.getName())
                    .build();
            List<DBDictionary.DictPair> pairs = new ArrayList<>();
            for (DictItemType item : dict.getItems().getDictItem()) {
                DBDictionary.DictPair dictPair = new DBDictionary.DictPair();
                dictPair.setKey(item.getId());
                dictPair.setName(item.getName());
                pairs.add(dictPair);
            }
            dictionary.setPairs(pairs.toArray(new DBDictionary.DictPair[pairs.size()]));
            dbDicts.add(dictionary);
        }
        Collections.sort(dbDicts);
        return Dictionaries.builder()
                .dicts(dbDicts.toArray(new DBDictionary[dbDicts.size()]))
                .build();
    }
}
