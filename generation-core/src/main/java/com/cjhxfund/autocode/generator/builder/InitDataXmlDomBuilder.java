package com.cjhxfund.autocode.generator.builder;

import com.cjhxfund.autocode.model.out.table.DBInitData;
import com.cjhxfund.autocode.wesklake.model.xsd.mysql.init.DataType;
import com.cjhxfund.autocode.wesklake.model.xsd.mysql.init.ObjectFactory;
import com.cjhxfund.autocode.wesklake.model.xsd.mysql.init.RowType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public final class InitDataXmlDomBuilder {

    public DataType createInitDataType(DBInitData initData) {
        ObjectFactory factory = new ObjectFactory();
        DataType dataType = factory.createDataType();
        dataType.setUpgradeMode(initData.getModeName());
        dataType.setEnname(initData.getTableName());
        dataType.setChname(initData.getTableCnName());
        factory.createData(dataType);
        dataType.setRow(createRows(factory, initData.getConfigParams()));
        return dataType;
    }

    private List<RowType> createRows(ObjectFactory factory, DBInitData.CfgPair[] params) {
        List<RowType>  rowTypes = new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
            RowType rowType = factory.createRowType();
            rowType.setId(String.valueOf(params[i].getId()));
            rowType.setParamValue(params[i].getValue());
            rowType.setRemark(params[i].getName());
            rowTypes.add(rowType);
        }
        return rowTypes;
    }


}
