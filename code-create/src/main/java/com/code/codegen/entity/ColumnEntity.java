package com.code.codegen.entity;

import lombok.Data;


@Data
public class ColumnEntity {
    /**
     * column name
     */
    private String columnName;
    /**
     * data type
     */
    private String dataType;
    /**
     * comments
     */
    private String comments;

    /**
     * case attribute name
     */
    private String caseAttrName;
    /**
     * lower attribute name
     */
    private String lowerAttrName;
    /**
     *  attribute type
     */
    private String attrType;
    /**
     *  other infos
     */
    private String extra;
}
