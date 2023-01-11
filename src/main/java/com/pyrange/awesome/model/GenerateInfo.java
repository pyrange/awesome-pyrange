package com.pyrange.awesome.model;


import java.util.List;
/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class GenerateInfo {

    private String basePackage;
    private String moduleName;
    private String tableComment;
    private String tableName;
    private String author;
    private String date;
    private List<GenerateColumnInfo> columnList;
    private List<String> importList;

    /**
     * model 信息
     */
    private String modelPath;
    private String modelNameUpperCamel;
    private String modelNameLowerCamel;
    private String modelNameLowercase;

    /**
     * 主键信息
     */
    private String primaryKeyJavaType;
    private String primaryKeyJavaTypeName;
    private String primaryKeyJdbcType;
    private String primaryKey;
    private String primaryKeyCamel;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getModelNameUpperCamel() {
        return modelNameUpperCamel;
    }

    public void setModelNameUpperCamel(String modelNameUpperCamel) {
        this.modelNameUpperCamel = modelNameUpperCamel;
    }

    public List<GenerateColumnInfo> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<GenerateColumnInfo> columnList) {
        this.columnList = columnList;
    }

    public List<String> getImportList() {
        return importList;
    }

    public void setImportList(List<String> importList) {
        this.importList = importList;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModelNameLowercase() {
        return modelNameLowercase;
    }

    public void setModelNameLowercase(String modelNameLowercase) {
        this.modelNameLowercase = modelNameLowercase;
    }

    public String getPrimaryKeyJavaType() {
        return primaryKeyJavaType;
    }

    public void setPrimaryKeyJavaType(String primaryKeyJavaType) {
        this.primaryKeyJavaType = primaryKeyJavaType;
    }

    public String getPrimaryKeyJavaTypeName() {
        return primaryKeyJavaTypeName;
    }

    public void setPrimaryKeyJavaTypeName(String primaryKeyJavaTypeName) {
        this.primaryKeyJavaTypeName = primaryKeyJavaTypeName;
    }

    public String getPrimaryKeyJdbcType() {
        return primaryKeyJdbcType;
    }

    public void setPrimaryKeyJdbcType(String primaryKeyJdbcType) {
        this.primaryKeyJdbcType = primaryKeyJdbcType;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getPrimaryKeyCamel() {
        return primaryKeyCamel;
    }

    public void setPrimaryKeyCamel(String primaryKeyCamel) {
        this.primaryKeyCamel = primaryKeyCamel;
    }

    public String getModelNameLowerCamel() {
        return modelNameLowerCamel;
    }

    public void setModelNameLowerCamel(String modelNameLowerCamel) {
        this.modelNameLowerCamel = modelNameLowerCamel;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }
}
