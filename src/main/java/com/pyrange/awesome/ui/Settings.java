package com.pyrange.awesome.ui;

import com.google.common.base.Throwables;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ui.Messages;
import com.pyrange.awesome.PyrangeConstant;
import com.pyrange.awesome.model.BasicConfig;
import com.pyrange.awesome.model.Result;
import com.pyrange.awesome.util.CommonUtil;
import com.pyrange.awesome.util.MessageUtil;
import com.pyrange.awesome.util.TableUtil;
import com.pyrange.awesome.util.TemplateUtil;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.*;
import java.util.List;

public class Settings extends JDialog {

    private static final Logger LOGGER = Logger.getInstance(Settings.class);

    public static final String PYRANGE_CODE_TEMPLATE = "PYRANGE-SETTINGS-codeTemplates";
    public static final String PYRANGE_SELECTED_CODE_TEMPLATE = "PYRANGE-SETTINGS-selectedCodeTemplates";

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldHost;
    private JTextField textFieldUserName;
    private JPasswordField textFieldPassword;
    private JComboBox comboBoxDatabase;
    private JTextField textFieldAuthor;
    private JTextField textFieldGroupId;
    private JComboBox jdkComboBox;
    private JComboBox codeTemplatesBox;
    private JButton setTemplateButton;
    private JRadioButton cloudConfigYes;
    private JRadioButton cloudConfigNo;
    private JButton loadCloudConfigButton;
    private JTextField cloudConfigUrl;

    public Settings() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        // 设置窗口位置
        this.setLocation(400, 200);

        BasicConfig basicConfig = BasicConfig.getBasicConfig();
        textFieldHost.setText(basicConfig.getJdbcHost());
        textFieldUserName.setText(basicConfig.getJdbcUserName());
        textFieldPassword.setText(basicConfig.getJdbcPassword());
        textFieldAuthor.setText(basicConfig.getAuthor());
        textFieldGroupId.setText(basicConfig.getGroupId());
        comboBoxDatabase.addItem(basicConfig.getJdbcDatabase());
        comboBoxDatabase.setSelectedItem(basicConfig.getJdbcDatabase());
        jdkComboBox.addItem("8");
        jdkComboBox.addItem("11");
        jdkComboBox.addItem("17");
        jdkComboBox.setSelectedItem(basicConfig.getJdkVersion().toString());

        // 模板设置
        for (String template : basicConfig.getCodeTemplates()) {
            codeTemplatesBox.addItem(template);
        }
        codeTemplatesBox.setSelectedItem(basicConfig.getSelectedCodeTemplate());
        if (basicConfig.isCloudConfigEnabled()) {
            cloudConfigYes.setSelected(true);
        } else {
            cloudConfigNo.setSelected(true);
        }
        cloudConfigUrl.setText(basicConfig.getCloudConfigUrl());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        comboBoxDatabase.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                String jdbcHost = textFieldHost.getText();
                String jdbcUserName = textFieldUserName.getText();
                String jdbcPassword = String.valueOf(textFieldPassword.getPassword());
                if (CommonUtil.isNullOrEmpty(jdbcHost)) {
                    MessageUtil.showErrorMsg("Host:Port required");
                    return;
                }
                if (CommonUtil.isNullOrEmpty(jdbcUserName)) {
                    MessageUtil.showErrorMsg("UserName required");
                    return;
                }
                if (CommonUtil.isNullOrEmpty(jdbcPassword)) {
                    MessageUtil.showErrorMsg("Password required");
                    return;
                }
                try {
                    TableUtil tableUtil = new TableUtil(jdbcHost, jdbcUserName, jdbcPassword);
                    List<String> allDatabase = tableUtil.getAllDatabase();
                    comboBoxDatabase.removeAllItems();
                    allDatabase.forEach(databaseName -> {
                        comboBoxDatabase.addItem(databaseName);
                    });
                } catch (Exception e1) {
                    MessageUtil.showErrorMsg(Throwables.getStackTraceAsString(e1));
                    LOGGER.info(e1);
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        setTemplateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TemplateSettings dialog = new TemplateSettings(codeTemplatesBox);
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        loadCloudConfigButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String cloudConfigUrlText = cloudConfigUrl.getText();
                if (StringUtils.isEmpty(cloudConfigUrlText)) {
                    Messages.showMessageDialog("please input your cloud config url", "error", Messages.getInformationIcon());
                    return;
                }
                TemplateUtil.loadCloudTemplate(cloudConfigUrlText);
            }
        });

    }

    private void onOK() {
        String check = checkSettingsParam();
        if (check != null) {
            Messages.showMessageDialog(check, "tip", Messages.getInformationIcon());
            return;
        }

        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.setValue("PYRANGE-SETTINGS-jdbcHost", textFieldHost.getText());
        propertiesComponent.setValue("PYRANGE-SETTINGS-jdbcDatabase", (String) comboBoxDatabase.getSelectedItem());
        propertiesComponent.setValue("PYRANGE-SETTINGS-jdbcUserName", textFieldUserName.getText());
        propertiesComponent.setValue("PYRANGE-SETTINGS-jdbcPassword", String.valueOf(textFieldPassword.getPassword()));
        propertiesComponent.setValue("PYRANGE-SETTINGS-author", textFieldAuthor.getText());
        propertiesComponent.setValue("PYRANGE-SETTINGS-groupId", textFieldGroupId.getText());
        propertiesComponent.setValue("PYRANGE-SETTINGS-settingsConfigured", true);
        propertiesComponent.setValue("PYRANGE-SETTINGS-jdkVersion", jdkComboBox.getSelectedItem().toString());
        propertiesComponent.setValue("PYRANGE-SETTINGS-cloudConfigUrl", cloudConfigUrl.getText());
        boolean cloudConfigYesSelected = cloudConfigYes.isSelected();
        propertiesComponent.setValue("PYRANGE-SETTINGS-cloudConfigEnabled", cloudConfigYesSelected);
        boolean haveCloudConfigSet = propertiesComponent.getBoolean("PYRANGE-SETTINGS-haveCloudConfigSet", false);
        if (cloudConfigYesSelected && !haveCloudConfigSet) {
            Messages.showMessageDialog("if you want to use cloud config, please load the cloud templates first", "tip", Messages.getInformationIcon());
            return;
        }
        setSelectedCodeTemplate(codeTemplatesBox.getSelectedItem().toString());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Settings dialog = new Settings();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private String checkSettingsParam() {
        if (CommonUtil.isNullOrEmpty(textFieldHost.getText())) {
            return "Host:Port required";
        }
        if (CommonUtil.isNullOrEmpty(textFieldUserName.getText())) {
            return "UserName required";
        }
        if (textFieldPassword.getPassword().length == 0) {
            return "Password required";
        }
        if (CommonUtil.isNullOrEmpty((String) comboBoxDatabase.getSelectedItem())) {
            return "Database required";
        }
        if (CommonUtil.isNullOrEmpty(textFieldAuthor.getText())) {
            return "author required";
        }
        if (CommonUtil.isNullOrEmpty(textFieldGroupId.getText())) {
            return "groupId required";
        }
        return null;
    }

    public static boolean settingsConfigured() {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        return propertiesComponent.getBoolean("PYRANGE-SETTINGS-settingsConfigured");
    }


    public static Result createNewTemplate(String newTemplateName) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        String[] codeTemplates = propertiesComponent.getValues(PYRANGE_CODE_TEMPLATE);
        for (String value : codeTemplates) {
            if (newTemplateName.equals(value)) {
                return Result.failure("the template name already exists");
            }
        }

        codeTemplates = insertElement(codeTemplates, newTemplateName, 0);
        propertiesComponent.setValues(PYRANGE_CODE_TEMPLATE, codeTemplates);
        return Result.success();
    }

    private static String[] insertElement(String[] original,
                                          String element, int index) {
        int length = original.length;
        String[] destination = new String[length + 1];
        System.arraycopy(original, 0, destination, 0, index);
        destination[index] = element;
        System.arraycopy(original, index, destination, index
                + 1, length - index);
        return destination;
    }

    /**
     * setSelectedCodeTemplate
     *
     * @param selectedCodeTemplate
     */
    public static void setSelectedCodeTemplate(String selectedCodeTemplate) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.setValue(PYRANGE_SELECTED_CODE_TEMPLATE, selectedCodeTemplate);
    }

    public JComboBox getCodeTemplatesBox() {
        return codeTemplatesBox;
    }
}
