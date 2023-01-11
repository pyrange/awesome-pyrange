package com.pyrange.awesome.model;


/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-8-6
 **/
public class ControllerGenerateInfo {
    private String basePackage;
    private String moduleName;
    private String moduleNameLower;
    private String tableComment;
    private String tableName;
    private String author;
    private String date;
    private String modelNameUpperCamel;
    private String modelNameLowerCamel;
    private String queryObjectName;
    private String baseRequestMapping;
    private String servicePackage;
    private String modelPackage;

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

    public String getModelNameLowerCamel() {
        return modelNameLowerCamel;
    }

    public void setModelNameLowerCamel(String modelNameLowerCamel) {
        this.modelNameLowerCamel = modelNameLowerCamel;
    }

    public String getBaseRequestMapping() {
        return baseRequestMapping;
    }

    public void setBaseRequestMapping(String baseRequestMapping) {
        this.baseRequestMapping = baseRequestMapping;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getQueryObjectName() {
        return queryObjectName;
    }

    public void setQueryObjectName(String queryObjectName) {
        this.queryObjectName = queryObjectName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleNameLower() {
        return moduleNameLower;
    }

    public void setModuleNameLower(String moduleNameLower) {
        this.moduleNameLower = moduleNameLower;
    }
}
