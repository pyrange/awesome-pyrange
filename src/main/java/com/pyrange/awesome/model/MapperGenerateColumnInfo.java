package com.pyrange.awesome.model;


/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/

public class MapperGenerateColumnInfo {
    private String columnComment;
    private String columnJavaTypeName;
    private String columnJdbcType;
    private String columnName;
    private String columnCamelName;

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

    public String getColumnJdbcType() {
        return columnJdbcType;
    }

    public void setColumnJdbcType(String columnJdbcType) {
        this.columnJdbcType = columnJdbcType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnCamelName() {
        return columnCamelName;
    }

    public void setColumnCamelName(String columnCamelName) {
        this.columnCamelName = columnCamelName;
    }
}
