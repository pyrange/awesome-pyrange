package com.inbyte.cg.model;

import java.util.List;
import java.util.Set;

/**
 * 
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class GenerateInfo {

    private String moduleName;
    private String moduleNameUppercaseCamel;
    private String moduleNameLowercase;
    private String moduleNameLowercaseCamel;
    private String moduleNameWithDot;
    private String moduleNameWithSlash;
    private String moduleNameWithHyphen;
    private String moduleNameWithUnderscore;

    private String tableComment;
    private String tableName;
    private String author;
    private String date;
    private List<GenerateColumnInfo> columnList;
    private Set<String> importList;

    /**
     * model 
     */
    private String modelPackage;
    /**
     *  controller 
     * com.pyrange.xx
     */
    private String controllerPackage;
    /**
     *  controller 
     * com/pyrange/xx
     */
    private String controllerPackageWithSlash;
    private String servicePackage;
    private String mapperPackage;

    private String modelNameUpperCamel;
    private String modelNameLowerCamel;
    private String modelNameLowercase;

    /**
     * 
     */
    private String primaryKeyJavaType;
    private String primaryKeyJavaTypeName;
    private String primaryKeyJdbcType;
    private String primaryKey;
    private String primaryKeyLowerCamel;
    private String primaryKeyUpperCamel;

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

    public Set<String> getImportList() {
        return importList;
    }

    public void setImportList(Set<String> importList) {
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

    public String getPrimaryKeyLowerCamel() {
        return primaryKeyLowerCamel;
    }

    public void setPrimaryKeyLowerCamel(String primaryKeyLowerCamel) {
        this.primaryKeyLowerCamel = primaryKeyLowerCamel;
    }

    public String getModelNameLowerCamel() {
        return modelNameLowerCamel;
    }

    public void setModelNameLowerCamel(String modelNameLowerCamel) {
        this.modelNameLowerCamel = modelNameLowerCamel;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getModuleNameLowercase() {
        return moduleNameLowercase;
    }

    public void setModuleNameLowercase(String moduleNameLowercase) {
        this.moduleNameLowercase = moduleNameLowercase;
    }

    public String getModuleNameWithDot() {
        return moduleNameWithDot;
    }

    public void setModuleNameWithDot(String moduleNameWithDot) {
        this.moduleNameWithDot = moduleNameWithDot;
    }

    public String getModuleNameWithSlash() {
        return moduleNameWithSlash;
    }

    public void setModuleNameWithSlash(String moduleNameWithSlash) {
        this.moduleNameWithSlash = moduleNameWithSlash;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getModuleNameUppercaseCamel() {
        return moduleNameUppercaseCamel;
    }

    public void setModuleNameUppercaseCamel(String moduleNameUppercaseCamel) {
        this.moduleNameUppercaseCamel = moduleNameUppercaseCamel;
    }

    public String getControllerPackageWithSlash() {
        return controllerPackageWithSlash;
    }

    public void setControllerPackageWithSlash(String controllerPackageWithSlash) {
        this.controllerPackageWithSlash = controllerPackageWithSlash;
    }

    public String getPrimaryKeyUpperCamel() {
        return primaryKeyUpperCamel;
    }

    public void setPrimaryKeyUpperCamel(String primaryKeyUpperCamel) {
        this.primaryKeyUpperCamel = primaryKeyUpperCamel;
    }

    public String getModuleNameWithHyphen() {
        return moduleNameWithHyphen;
    }

    public void setModuleNameWithHyphen(String moduleNameWithHyphen) {
        this.moduleNameWithHyphen = moduleNameWithHyphen;
    }

    public String getModuleNameWithUnderscore() {
        return moduleNameWithUnderscore;
    }

    public void setModuleNameWithUnderscore(String moduleNameWithUnderscore) {
        this.moduleNameWithUnderscore = moduleNameWithUnderscore;
    }

    public String getModuleNameLowercaseCamel() {
        return moduleNameLowercaseCamel;
    }

    public void setModuleNameLowercaseCamel(String moduleNameLowercaseCamel) {
        this.moduleNameLowercaseCamel = moduleNameLowercaseCamel;
    }
}
