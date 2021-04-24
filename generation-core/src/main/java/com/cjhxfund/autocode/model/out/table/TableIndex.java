package com.cjhxfund.autocode.model.out.table;

import com.cjhxfund.autocode.wesklake.model.xsd.table.TableIndexType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Created by Jeffrey on 2021/3/14.
 */
@Data
@Builder
@ToString
public class TableIndex implements Serializable {

    private String indexName;
    private String fieldName;
    private String dbType;
    private String indexType;
    /**
     * NUMBER OF INDEX IN HXX HEADER FILE TO GENERATE FINDING METHOD
     */
    private String no;

    public static Function<TableIndexType, TableIndex> processor = new Function<TableIndexType, TableIndex>() {

        @Override
        public TableIndex apply(TableIndexType fieldType) {
            if (fieldType == null) {
                return null;
            }
            return TableIndex.builder()
                    .dbType(fieldType.getUseway())
                    .no(fieldType.getName())
                    .indexName(fieldType.getName())
                    .indexType(fieldType.getIndexType())
                    .fieldName(fieldType.getFieldNames())
                    .build();
        }
    };
}
