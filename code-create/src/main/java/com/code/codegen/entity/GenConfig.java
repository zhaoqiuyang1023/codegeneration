package com.code.codegen.entity;

import lombok.Data;

import java.util.List;


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
     *  table prefix
     */
    private String tablePrefix;

    /**
     *  table name
     */
    private List<String> tableName;

    /**
     *  comments
     */
    private String comments;



}
