package com.code.codegen.entity;

import lombok.Data;


@Data
public class GenConfig {

    /**
     *  package name
     */
    private String packageName;

    /**
     *  author
     */
    private String author;

    /**
     *  module name
     */
    private String moduleName;

    /**
     *  table prefix
     */
    private String tablePrefix;

    /**
     *  table name
     */
    private String tableName;

    /**
     *  comments
     */
    private String comments;
}
