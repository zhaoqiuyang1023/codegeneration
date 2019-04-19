package com.code.codegen.util;

import java.util.HashMap;

public class DataTypeUtils {
    static HashMap<String, String> map = new HashMap<>();

    static {
        map.put("tinyint", "Boolean");
        map.put("smallint", "Integer");
        map.put("mediumint", "Integer");
        map.put("int", "Integer");
        map.put("integer", "Integer");
        map.put("bigint", "Long");
        map.put("float", "Float");
        map.put("double", "Double");
        map.put("decimal", "BigDecimal");
        map.put("bit", "Boolean");
        map.put("char", "String");
        map.put("varchar", "String");
        map.put("tinytext", "String");
        map.put("text", "String");
        map.put("mediumtext", "String");
        map.put("longtext", "String");
        map.put("date", "Date");
        map.put("datetime", "Date");
        map.put("timestamp", "Date");
    }


    public static String getDataType(String dataType) {
        return map.get(dataType);
    }
}
