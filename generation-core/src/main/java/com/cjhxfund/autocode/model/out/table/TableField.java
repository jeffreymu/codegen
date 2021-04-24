package com.cjhxfund.autocode.model.out.table;

import com.cjhxfund.autocode.model.BasicType;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableFieldType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.function.Function;

/**
 * Created by Jeffrey on 2021/3/14.
 */
@Data
@Builder
@ToString
public class TableField implements Comparable<TableField> {

    private String name;
    private String type;
    private BasicType origType;
    private String length;
    private String precision;
    private String defaultValue;
    private String nullable;

    public static Function<TableFieldType, TableField> processor = new Function<TableFieldType, TableField>() {

        @Override
        public TableField apply(TableFieldType fieldType) {
            return TableField
                    .builder()
                    .name(fieldType.getFieldName())
                    .nullable(fieldType.getNullable())
                    .build();
        }
    };

    public void defaultValue(String dbType, String precision, String nullable) {
        if (dbType.equals("varchar2")) {
            this.setDefaultValue("\' \'");
        } else if (dbType.equals("number")) {
            this.setDefaultValue("0");
        }

        if (dbType.equals("number")) {
            this.setPrecision(precision);
        }

        if (nullable.equals("false")) {
            this.setNullable("not null");
        } else {
            this.setNullable("null");
        }
    }

    @Override
    public int compareTo(TableField o) {
        return this.getName().compareTo(o.getName());
    }

}
