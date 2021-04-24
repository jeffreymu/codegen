package com.cjhxfund.autocode.model.converter;

import com.cjhxfund.autocode.model.BasicType;

public class BoostTypeConverter {

    public static String convert2Boost(BasicType type) {

        if (type == BasicType.STRING) {
            return "const char *";
        }

        if (type == BasicType.CHAR) {
            return "char";
        }

        if (type == BasicType.INT16 || type == BasicType.INT32 || type == BasicType.INT64) {
            return convert2BoostIntType(type);
        }

        return "";
    }

    public static String convert2Struct(BasicType type) {

        if (type == BasicType.CHAR || type == BasicType.STRING) {
            return "char";
        }

        if (type == BasicType.DOUBLE) {
            return "double";
        }

        if (type == BasicType.INT16 || type == BasicType.INT32 || type == BasicType.INT64) {
            return convert2BoostIntType(type);
        }

        return "";
    }

    public static String convert2BoostIntType(BasicType type) {
        if (type == BasicType.INT16) {
            return "boost::int16_t";
        }

        if (type == BasicType.INT32) {
            return "boost::int32_t";
        }

        if (type == BasicType.INT64) {
            return "boost::int64_t";
        }

        return "";
    }
}
