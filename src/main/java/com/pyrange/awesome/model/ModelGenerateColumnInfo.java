package com.pyrange.awesome.model;

/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/

public class ModelGenerateColumnInfo {
    /**
     * 字段注释
     */
    private String columnComment;
    /**
     * 字段类型名称
     */
    private String columnJavaTypeName;
    /**
     * 驼峰名
     */
    private String columnCamelName;
    /**
     * 允许为空
     */
    private String nullable;
    /**
     * 字符最大长度
     */
    private String characterMaximumLength;

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnJavaTypeName() {
        return columnJavaTypeName;
    }

    public void setColumnJavaTypeName(String columnJavaTypeName) {
        this.columnJavaTypeName = columnJavaTypeName;
    }

    public String getColumnCamelName() {
        return columnCamelName;
    }

    public void setColumnCamelName(String columnCamelName) {
        this.columnCamelName = columnCamelName;
    }

    public String getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public void setCharacterMaximumLength(String characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }
}
