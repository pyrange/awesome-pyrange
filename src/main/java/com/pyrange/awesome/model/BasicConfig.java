package com.pyrange.awesome.model;

/**
 * 基本配置类
 *
 * @author chenjw
 * @date 2023/1/16
 */
public class BasicConfig {

    private String jdbcHost;
    private String jdbcDatabase;
    private String jdbcUserName;
    private String jdbcPassword;

    private String author;

    /**
     * 组织ID
     * com.xx
     */
    private String groupId;
    /**
     * JDK 版本
     */
    private Integer jdkVersion;
    /**
     * 结果包装类名
     */
    private String resultClassName;
    /**
     * 结果包装类引用
     */
    private String resultClassReference;
    /**
     * 分页类名
     */
    private String pageUtilClassName;
    /**
     * 分页类引用
     */
    private String pageClassUtilReference;
    /**
     * 分页基类名
     */
    private String basePageClassName;
    /**
     * 分页基类引用
     */
    private String basePageClassReference;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getJdkVersion() {
        return jdkVersion;
    }

    public void setJdkVersion(Integer jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public String getResultClassName() {
        return resultClassName;
    }

    public void setResultClassName(String resultClassName) {
        this.resultClassName = resultClassName;
    }

    public String getResultClassReference() {
        return resultClassReference;
    }

    public void setResultClassReference(String resultClassReference) {
        this.resultClassReference = resultClassReference;
    }

    public String getPageUtilClassName() {
        return pageUtilClassName;
    }

    public void setPageUtilClassName(String pageUtilClassName) {
        this.pageUtilClassName = pageUtilClassName;
    }

    public String getPageUtilClassReference() {
        return pageClassUtilReference;
    }

    public void setPageClassUtilReference(String pageClassUtilReference) {
        this.pageClassUtilReference = pageClassUtilReference;
    }

    public String getBasePageClassName() {
        return basePageClassName;
    }

    public void setBasePageClassName(String basePageClassName) {
        this.basePageClassName = basePageClassName;
    }

    public String getBasePageClassReference() {
        return basePageClassReference;
    }

    public void setBasePageClassReference(String basePageClassReference) {
        this.basePageClassReference = basePageClassReference;
    }
}
