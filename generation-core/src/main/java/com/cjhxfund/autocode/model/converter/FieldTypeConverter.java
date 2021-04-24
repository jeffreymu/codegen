package com.cjhxfund.autocode.model.converter;

import com.cjhxfund.autocode.model.BasicType;

public class FieldTypeConverter {

    public static String convert2Message(BasicType type) {

        if (type == BasicType.CHAR || type == BasicType.STRING) {
            return "string";
        }

        if (type == BasicType.DOUBLE) {
            return "double";
        }

        if (type == BasicType.INT16 || type == BasicType.INT32 || type == BasicType.INT64) {
            return convert2PBIntType(type);
        }

        return "";
    }

    public static String convert2PBIntType(BasicType type) {
        if (type == BasicType.INT16 || type == BasicType.INT32) {
            return "int32";
        }

        if (type == BasicType.INT64) {
            return "int64";
        }

        return "";
    }
}
