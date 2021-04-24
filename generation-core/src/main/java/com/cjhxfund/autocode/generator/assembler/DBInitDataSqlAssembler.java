package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.model.out.table.DBInitData;
import com.cjhxfund.autocode.wesklake.model.xsd.base.BasicDataType;
import com.cjhxfund.autocode.wesklake.model.xsd.base.TetfmsysparamType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class DBInitDataSqlAssembler implements ContentFileAssembler<DBInitData, BasicDataType> {

    @Override
    public DBInitData assemble(String subSystem, String moduleName, String destFileName, BasicDataType dictType) {
        String tableName = dictType.getRelationTableName();
        List<TetfmsysparamType> sysParams = dictType.getDataXml().getDocumentElement().getTetfmsysparam();
        DBInitData initData = DBInitData.builder()
                .tableName(tableName)
                .tableCnName(destFileName)
                .modeName(dictType.getUpdateMode())
                .build();
        if (sysParams == null || sysParams.isEmpty()) {
            return initData;
        }
        List<DBInitData.CfgPair> cfgPairs = new ArrayList<>();
        for (TetfmsysparamType param : sysParams) {
            DBInitData.CfgPair cfgPair = new DBInitData.CfgPair();
            cfgPair.setId(Integer.parseInt(param.getId()));
            cfgPair.setValue(param.getParamValue());
            cfgPair.setName(param.getRemark());
            cfgPairs.add(cfgPair);
        }
        initData.setConfigParams(cfgPairs.toArray(new DBInitData.CfgPair[cfgPairs.size()]));
        return initData;
    }
}
