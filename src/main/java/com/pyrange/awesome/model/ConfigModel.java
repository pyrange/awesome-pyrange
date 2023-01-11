package com.pyrange.awesome.model;

/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class ConfigModel {
    private String jdbcHost;
    private String jdbcDatabase;
    private String jdbcUserName;
    private String jdbcPassword;

    private String tableName;

    private String author;
    private String modelPath;
    private String projectPath;
    private String mapperJavaPath;
    private String mapperXmlPath;
    private String controllerPath;
    private String servicePath;


    private Boolean generateModel;
    private Boolean generateMapper;
    private Boolean generateControllerService;
    private Boolean generateFrontEnd;


    public String getJdbcHost() {
        return jdbcHost;
    }

    public void setJdbcHost(String jdbcHost) {
        this.jdbcHost = jdbcHost;
    }

    public String getJdbcDatabase() {
        return jdbcDatabase;
    }

    public void setJdbcDatabase(String jdbcDatabase) {
        this.jdbcDatabase = jdbcDatabase;
    }

    public String getJdbcUserName() {
        return jdbcUserName;
    }

    public void setJdbcUserName(String jdbcUserName) {
        this.jdbcUserName = jdbcUserName;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
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

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getMapperJavaPath() {
        return mapperJavaPath;
    }

    public void setMapperJavaPath(String mapperJavaPath) {
        this.mapperJavaPath = mapperJavaPath;
    }

    public String getMapperXmlPath() {
        return mapperXmlPath;
    }

    public void setMapperXmlPath(String mapperXmlPath) {
        this.mapperXmlPath = mapperXmlPath;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public Boolean getGenerateModel() {
        return generateModel;
    }

    public void setGenerateModel(Boolean generateModel) {
        this.generateModel = generateModel;
    }

    public Boolean getGenerateMapper() {
        return generateMapper;
    }

    public void setGenerateMapper(Boolean generateMapper) {
        this.generateMapper = generateMapper;
    }

    public Boolean getGenerateControllerService() {
        return generateControllerService;
    }

    public void setGenerateControllerService(Boolean generateControllerService) {
        this.generateControllerService = generateControllerService;
    }

    public Boolean getGenerateFrontEnd() {
        return generateFrontEnd;
    }

    public void setGenerateFrontEnd(Boolean generateFrontEnd) {
        this.generateFrontEnd = generateFrontEnd;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }
}
