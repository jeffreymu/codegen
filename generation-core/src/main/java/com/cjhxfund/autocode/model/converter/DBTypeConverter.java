package com.cjhxfund.autocode.model.converter;

import com.cjhxfund.autocode.model.BasicType;

public class DBTypeConverter {

    public static String convert(BasicType type) {

        if (type == BasicType.STRING) {
            return "varchar2";
        }

        if (type == BasicType.INT16 || type == BasicType.INT32 || type == BasicType.INT64 || type == BasicType.DOUBLE) {
            return "number";
        }

        if (type == BasicType.CHAR) {
            return "char";
        }

        return "";
    }
}
