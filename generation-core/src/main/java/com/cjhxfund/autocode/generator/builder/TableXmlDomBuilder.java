package com.cjhxfund.autocode.generator.builder;

import com.cjhxfund.autocode.model.DBIndexTypeConst;
import com.cjhxfund.autocode.model.out.table.DBTable;
import com.cjhxfund.autocode.model.out.table.TableField;
import com.cjhxfund.autocode.model.out.table.TableIndex;
import com.cjhxfund.autocode.wesklake.model.xsd.mysql.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public final class TableXmlDomBuilder {

    public TableType createTableType(DBTable dbTable) {
        ObjectFactory factory = new ObjectFactory();
        TableType tableType = factory.createTableType();
        tableType.setEnname(dbTable.getName());
        tableType.setChname(dbTable.getChineseName());
        factory.createTable(tableType);
        ColumnsType columnsType = createColumns(factory, dbTable.getFields());
        IndexsType indexsType = createIndexes(factory, dbTable.getName(), dbTable.getAllIndexes());
        tableType.setColumns(columnsType);
        tableType.setIndexs(indexsType);
        return tableType;
    }

    private ColumnsType createColumns(ObjectFactory factory, TableField[] fields) {
        ColumnsType columnsType = factory.createColumnsType();
        List<ItemType> colItems = columnsType.getItem();
        for (int i = 0; i < fields.length; i++) {
            ItemType item = new ItemType();
            item.setName(fields[i].getName());
            item.setType(fields[i].getType());
            item.setLength(fields[i].getLength());
            item.setPrecision(fields[i].getPrecision());
            item.setNullable(fields[i].getNullable());
            item.setDefaultValue(fields[i].getDefaultValue().equals("' '") ? "" : fields[i].getDefaultValue());
            colItems.add(item);
        }
        return columnsType;
    }

    private IndexsType createIndexes(ObjectFactory factory, String tableName, TableIndex[] indexes) {
        IndexsType indexType = factory.createIndexsType();
        List<ItemType> indItems = indexType.getItem();
        int uIndexOrder = 0;
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i].getIndexType().equalsIgnoreCase(DBIndexTypeConst.UN_UNIQUE_INDEX_TYPE_CN_NAME)) {
                continue;
            }
            if (indexes[i].getIndexType().equalsIgnoreCase(DBIndexTypeConst.UNIQUE_INDEX_TYPE_CN_NAME)) {
                uIndexOrder += 1;
            }
            ItemType item = new ItemType();
            String type = indexType(indexes[i].getIndexType());
            if (type.equals(IndexType.primary.name())) {
                item.setName("pk_" + tableName);
            } else {
                item.setName("uidx_" + tableName + "_" + uIndexOrder);
            }
            item.setType(type);
            item.setColumns(indexes[i].getFieldName());
            indItems.add(item);
        }
        return indexType;
    }

    private String indexType(String type) {
        if (type.equalsIgnoreCase(DBIndexTypeConst.PK_INDEX_TYPE_CN_NAME)) {
            return IndexType.primary.name();
        }
        if (type.equalsIgnoreCase(DBIndexTypeConst.UNIQUE_INDEX_TYPE_CN_NAME)) {
            return IndexType.uindex.name();
        }
        return "";
    }

    public enum IndexType {
        primary, uindex
    }
}
