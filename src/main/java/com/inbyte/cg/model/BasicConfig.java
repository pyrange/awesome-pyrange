package com.inbyte.cg.model;

import com.inbyte.cg.PyrangeConstant;
import com.intellij.ide.util.PropertiesComponent;
import org.apache.commons.lang.StringUtils;

/**
 *
 *
 * @author chenjw
 * @date 2023/1/16
 */
public class BasicConfig {


    public static final String PYRANGE_CODE_TEMPLATE = "PYRANGE-SETTINGS-codeTemplates";
    public static final String PYRANGE_SELECTED_CODE_TEMPLATE = "PYRANGE-SETTINGS-selectedCodeTemplates";
    public static final String PYRANGE_CLOUD_TEMPLATE_NAME = "cloud";

    private String jdbcHost;
    private String jdbcDatabase;
    private String jdbcUserName;
    private String jdbcPassword;

    private String author;

    /**
     * ID
     * com.xx
     */
    private String groupId;
    /**
     * JDK
     */
    private Integer jdkVersion;

    /**
     *
     */
    private String[] codeTemplates;
    /**
     *
     */
    private String selectedCodeTemplate;

    /**
     *
     */
    private boolean cloudConfigEnabled;

    /**
     * URL
     */
    private String cloudConfigUrl;


    public static BasicConfig getBasicConfig() {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        BasicConfig basicConfig = new BasicConfig();
        basicConfig.setJdbcHost(propertiesComponent.getValue("PYRANGE-SETTINGS-jdbcHost"));
        basicConfig.setJdbcDatabase(propertiesComponent.getValue("PYRANGE-SETTINGS-jdbcDatabase"));
        basicConfig.setJdbcUserName(propertiesComponent.getValue("PYRANGE-SETTINGS-jdbcUserName"));
        basicConfig.setJdbcPassword(propertiesComponent.getValue("PYRANGE-SETTINGS-jdbcPassword"));
        String groupId = StringUtils.isEmpty(propertiesComponent.getValue("PYRANGE-SETTINGS-groupId")) ? PyrangeConstant.DEFAULT_GROUP_ID : propertiesComponent.getValue("PYRANGE-SETTINGS-groupId");
        basicConfig.setGroupId(groupId);
        basicConfig.setAuthor(propertiesComponent.getValue("PYRANGE-SETTINGS-author"));
        basicConfig.setJdkVersion(propertiesComponent.getInt("PYRANGE-SETTINGS-jdkVersion", 11));
        basicConfig.setCloudConfigEnabled(propertiesComponent.getBoolean("PYRANGE-SETTINGS-cloudConfigEnabled", false));
        basicConfig.setCloudConfigUrl(propertiesComponent.getValue("PYRANGE-SETTINGS-cloudConfigUrl"));

        String[] codeTemplate = propertiesComponent.getValues(PYRANGE_CODE_TEMPLATE);
        if (codeTemplate == null) {
            codeTemplate = new String[]{"default"};
            propertiesComponent.setValues(PYRANGE_CODE_TEMPLATE, codeTemplate);
        }
        basicConfig.setCodeTemplates(codeTemplate);
        String selectedCodeTemplate = propertiesComponent.getValue(PYRANGE_SELECTED_CODE_TEMPLATE);
        if (StringUtils.isEmpty(selectedCodeTemplate)) {
            selectedCodeTemplate = "default";
        }
        basicConfig.setSelectedCodeTemplate(selectedCodeTemplate);

        return basicConfig;
    }

    /**
     *
     * @return
     */
    public static String getTemplateCollectionName() {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        boolean cloudConfigEnabled = propertiesComponent.getBoolean("PYRANGE-SETTINGS-cloudConfigEnabled", false);
        if (cloudConfigEnabled) {
            return PYRANGE_CLOUD_TEMPLATE_NAME;
        }

        String selectedCodeTemplate = propertiesComponent.getValue(PYRANGE_SELECTED_CODE_TEMPLATE);
        if (StringUtils.isEmpty(selectedCodeTemplate)) {
            return  "default";
        }
        return selectedCodeTemplate;
    }

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

    public String[] getCodeTemplates() {
        return codeTemplates;
    }

    public void setCodeTemplates(String[] codeTemplates) {
        this.codeTemplates = codeTemplates;
    }

    public String getSelectedCodeTemplate() {
        return selectedCodeTemplate;
    }

    public void setSelectedCodeTemplate(String selectedCodeTemplate) {
        this.selectedCodeTemplate = selectedCodeTemplate;
    }

    public boolean isCloudConfigEnabled() {
        return cloudConfigEnabled;
    }

    public void setCloudConfigEnabled(boolean cloudConfigEnabled) {
        this.cloudConfigEnabled = cloudConfigEnabled;
    }

    public String getCloudConfigUrl() {
        return cloudConfigUrl;
    }

    public void setCloudConfigUrl(String cloudConfigUrl) {
        this.cloudConfigUrl = cloudConfigUrl;
    }
}
