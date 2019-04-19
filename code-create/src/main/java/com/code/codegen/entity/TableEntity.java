package com.code.codegen.entity;

import lombok.Data;

import java.util.List;


@Data
public class TableEntity {

    /**
     * table Name
     */
    private String tableName;
    /**
     * comments
     */
    private String comments;
    /**
     * primary key
     */
    private ColumnEntity pk;
    /**
     * columns
     */
    private List<ColumnEntity> columns;
    /**
     * case type
     */
    private String caseClassName;
    /**
     * lower type
     */
    private String lowerClassName;
}
