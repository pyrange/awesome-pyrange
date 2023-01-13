package com.pyrange.awesome.model;

/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/

public class GenerateColumnInfo {

    /**
     * 字段名
     */
    private String columnName;
    /**
     * 小写开头驼峰名
     */
    private String columnCamelName;
    /**
     * 大写开头驼峰名
     */
    private String columnUpperCamelName;
    /**
     * 字段注释
     */
    private String columnComment;
    /**
     * 字段类型
     */
    private String columnJdbcType;
    /**
     * 字段类型名称
     */
    private String columnJavaTypeName;
    /**
     * 允许为空
     */
    private String nullable;
    /**
     * 字符最大长度
     */
    private String characterMaximumLength;
    /**
     * set 方法名称
     */
    private String setterName;
    /**
     * get 方法名称
     */
    private String getterName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

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

    public String getColumnJdbcType() {
        return columnJdbcType;
    }

    public void setColumnJdbcType(String columnJdbcType) {
        this.columnJdbcType = columnJdbcType;
    }

    public String getSetterName() {
        return setterName;
    }

    public void setSetterName(String setterName) {
        this.setterName = setterName;
    }

    public String getGetterName() {
        return getterName;
    }

    public void setGetterName(String getterName) {
        this.getterName = getterName;
    }

    public String getColumnUpperCamelName() {
        return columnUpperCamelName;
    }

    public void setColumnUpperCamelName(String columnUpperCamelName) {
        this.columnUpperCamelName = columnUpperCamelName;
    }
}
