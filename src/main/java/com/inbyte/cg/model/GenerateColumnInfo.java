package com.inbyte.cg.model;

/**
 * 
 *
 * @author : yangning
 * @date: 2018-6-11
 **/

public class GenerateColumnInfo {

    /**
     * 
     */
    private String columnName;
    /**
     * 
     */
    private String columnCamelName;
    /**
     * 
     */
    private String columnUpperCamelName;
    /**
     * 
     */
    private String columnComment;
    /**
     * 
     */
    private String columnJdbcType;
    /**
     * Java
     */
    private String columnJavaTypeName;
    /**
     * 
     */
    private String nullable;
    /**
     * 
     */
    private String characterMaximumLength;
    /**
     * set 
     */
    private String setterName;
    /**
     * get 
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
