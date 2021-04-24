package com.cjhxfund.autocode.generator.builder;

import com.cjhxfund.autocode.cache.SystemFieldsRetriever;
import com.cjhxfund.autocode.model.BasicType;
import com.cjhxfund.autocode.model.DBIndexTypeConst;
import com.cjhxfund.autocode.model.converter.BoostTypeConverter;
import com.cjhxfund.autocode.model.out.mdb.DBRegisterIndex;
import com.cjhxfund.autocode.model.out.table.DBTable;
import com.cjhxfund.autocode.model.out.table.TableIndex;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.StdtypeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public final class TableIndexBuilder {

    @Autowired
    private SystemFieldsRetriever fieldsRetriever;

    public String buildIndex(TableIndex tIndex) {
        String fields = tIndex.getFieldName();
        String[] fieldArray = fields.split(",");
        StringBuilder pkIndexBuilder = new StringBuilder();
        for (String field : fieldArray) {
            String boostType = buildSingleBoostType(field);
            pkIndexBuilder.append(boostType).append(" ").append(field).append(", ");
        }
        return pkIndexBuilder.deleteCharAt(pkIndexBuilder.length() - 2).toString().trim();
    }

    public String buildRemoveKey(TableIndex tIndex) {
        String fields = tIndex.getFieldName();
        String[] fieldArray = fields.split(",");
        StringBuilder pkIndexBuilder = new StringBuilder();
        for (String field : fieldArray) {
            String boostType = buildSingleBoostType(field);
            if (boostType.endsWith("*")) {
                pkIndexBuilder.append(boostType).append(field.trim()).append(", ");
            } else {
                pkIndexBuilder.append(boostType).append(" ").append(field.trim()).append(", ");
            }
        }
        return pkIndexBuilder.toString();
    }

    public String buildSingleBoostType(String field) {
        StdtypeType stdType = fieldsRetriever.findField(field.trim());
        String boostType = BoostTypeConverter.convert2Boost(BasicType.valueOf(stdType.getBasicType()));
        return boostType;
    }

    public List<DBRegisterIndex> buildRegisterIndex(DBTable dbTable, String tableID, boolean needCheck) {
        List<DBRegisterIndex> indexList = new ArrayList<>();
        TableIndex[] tableIndexes = dbTable.getAllIndexes();
        for (TableIndex index : tableIndexes) {
            indexList.add(DBRegisterIndex
                    .builder()
                    .tableId(needCheck ? Integer.parseInt("1000" + tableID) : Integer.parseInt(tableID))
                    .id(Integer.parseInt(index.getIndexName()))
                    .flag((index.getIndexType().equals(DBIndexTypeConst.PK_INDEX_TYPE_CN_NAME)
                            || index.getIndexType().equals(DBIndexTypeConst.UNIQUE_INDEX_TYPE_CN_NAME)) ? true : false)
                    .fields(index.getFieldName())
                    .build()
            );
        }
        return indexList;
    }
}
