package com.cjhxfund.autocode.ast.parser;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

    public static final String C99_BASE_TYPE[] = {"char", "unsigned char", "signed char", "int", "unsigned int", "short", "unsigned short", "long", "unsigned long", "float", "double", "long double", "void", "_Bool", "_Complex", "_Imaginary"};

    public static final String CPP14_BASE_TYPE[] = {"char", "unsigned char", "signed char", "unsigned int", "signed int", "short int", "unsigned short int", "signed short int", "long int", "signed long int", "unsigned long int", "float", "double", "long double", "wchar_t"};

    public static final String C99_FORMAT_OUTPUT[] = {"%a", "%A", "%c", "%C", "%d", "%ld", "%Ld", "%hd", "%e ", "%E", "%f", "%g", "%G", "%i", "%o", "%p", "%s", "%S", "%u", "%x", "%#x", "%X", "%#X", "%%", "%lld", "%llu", "%llx", "%I64d", "%I64u", "%I64x"};

    public static final boolean isContainFORMAT_OUTPUT(String str, Vector<String> vec) {
        for (String tmp : C99_FORMAT_OUTPUT
                ) {
            int count = 0;
            Pattern p = Pattern.compile(tmp);
            Matcher m = p.matcher(str);
            while (m.find()) {
                count++;
            }
            for (int i = 0; i < count; i++) {
                vec.add(tmp);
            }
        }
        return vec.isEmpty();
    }

    public static final String ESCAPE_CHARACTERS[] = {"\\a", "\\b", "\\f", "\\n", "\\r", "\\t", "\\v", "\\\\", "\\'", "\\\"", "\\0", "\\?"};

    public static final String FUNCTION_NAME = "function_name";

    public static final String FUNCTION_CALL = "function_call_name";

    public static final String FORMAL_PARAMETER = "argument_name";

    public static final String ACTUAL_PARAMETER = "XXX_actual_parameter";

    public static final String VARIABLE_NAMW = "variable_name";

    public static final String VARIABLE_CALL = "XXX_variable_call";

    public static final String STRING = "\"thisisstring\"";
    public static final String CHAR = "\'C\'";

    public static final String CLASS_NAME = "class_name";

    public static final String TEMPLATE_NAME = "XXX_template_arg_name";

}
