package com.pyrange.awesome.model;


import java.util.List;
/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class ModelGenerateInfo {
    private String basePackage;
    private String moduleName;
    private String tableComment;
    private String tableName;
    private String author;
    private String date;
    private String modelNameUpperCamel;
    private String modelNameLowercase;
    private List<ModelGenerateColumnInfo> columnList;
    private List<String> importList;

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

    public List<ModelGenerateColumnInfo> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ModelGenerateColumnInfo> columnList) {
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
}
