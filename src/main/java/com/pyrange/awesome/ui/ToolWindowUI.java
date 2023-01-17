package com.pyrange.awesome.ui;

import com.google.common.base.Throwables;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.pyrange.awesome.PyrangeException;
import com.pyrange.awesome.util.MessageUtil;
import com.pyrange.awesome.generate.CodeGenerate;
import com.pyrange.awesome.model.ConfigModel;
import com.pyrange.awesome.util.CommonUtil;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.Document;
import java.awt.event.*;
import java.io.File;
import java.util.List;

public class ToolWindowUI {

    private static final String SEPARATOR_LINE = "\n\n" + "--------------------------------------------------------------------------------------------------------------------------------------------------------" + "\n\n";

    private JPanel toolWindowContent;

    private JTextField textFieldTableName;
    private JTextField textFieldProjectPath;

    private JComboBox comboBoxServicePath;
    private JComboBox comboBoxModelPath;
    private JComboBox comboBoxMapperPath;
    private JComboBox comboBoxControllerPath;

    private JButton goButton;
    private JButton modelPathSelectButton;
    private JButton mapperPathSelectButton;
    private JButton projectPathSelectButton;
    private JButton servicePathSelectButton;
    private JButton controllerPathSelectButton;

    private JCheckBox controllerCheckBox;
    private JCheckBox serviceCheckBox;
    private JCheckBox modelCheckBox;
    private JCheckBox mapperCheckBox;
    private JCheckBox frontEndCheckBox;
    private JCheckBox testCheckBox;
    private JCheckBox samePathCheckBox;

    private JButton viewControllerButton;
    private JButton viewServiceButton;
    private JButton viewModelButton;
    private JButton viewMapperButton;
    private JButton viewFEButton;
    private JButton viewTestButton;
    private JLabel settingLabel;


    private String baseProjectPath;

    public JPanel getToolWindowContent() {
        return this.toolWindowContent;
    }

    private static final Logger LOGGER = Logger.getInstance(ToolWindowUI.class);

    public ToolWindowUI(Project project) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();

        this.baseProjectPath = project.getBasePath();
        // 初始化checkbox
        controllerCheckBox.setSelected(propertiesComponent.getBoolean("PYRNAGE-WINDOWS-controllerCheckBox", true));
        serviceCheckBox.setSelected(propertiesComponent.getBoolean("PYRNAGE-WINDOWS-serviceCheckBox", true));
        mapperCheckBox.setSelected(propertiesComponent.getBoolean("PYRNAGE-WINDOWS-mapperCheckBox", true));
        modelCheckBox.setSelected(propertiesComponent.getBoolean("PYRNAGE-WINDOWS-modelCheckBox", true));
        testCheckBox.setSelected(propertiesComponent.getBoolean("PYRNAGE-WINDOWS-testCheckBox", true));
        frontEndCheckBox.setSelected(propertiesComponent.getBoolean("PYRNAGE-WINDOWS-frontEndCheckBox", true));

        textFieldTableName.setText(propertiesComponent.getValue("Pyrange-WINDOWStableName"));
        textFieldProjectPath.setText(propertiesComponent.getValue("Pyrange-WINDOWSprojectPath"));

        comboBoxControllerPath.addItem(propertiesComponent.getValue("Pyrange-WINDOWScontrollerPath"));
        comboBoxControllerPath.setSelectedItem(propertiesComponent.getValue("Pyrange-WINDOWScontrollerPath"));

        comboBoxServicePath.addItem(propertiesComponent.getValue("Pyrange-WINDOWSservicePath"));
        comboBoxServicePath.setSelectedItem(propertiesComponent.getValue("Pyrange-WINDOWSservicePath"));

        comboBoxModelPath.addItem(propertiesComponent.getValue("Pyrange-WINDOWSmodelPath"));
        comboBoxModelPath.setSelectedItem(propertiesComponent.getValue("Pyrange-WINDOWSmodelPath"));

        comboBoxMapperPath.addItem(propertiesComponent.getValue("Pyrange-WINDOWSmapperPath"));
        comboBoxMapperPath.setSelectedItem(propertiesComponent.getValue("Pyrange-WINDOWSmapperPath"));

        comboBoxControllerPath.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                comboBoxClickAction(comboBoxControllerPath, "controller");
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        comboBoxServicePath.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                comboBoxClickAction(comboBoxServicePath, "service");
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        comboBoxModelPath.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                comboBoxClickAction(comboBoxModelPath, "model");
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        comboBoxMapperPath.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                comboBoxClickAction(comboBoxMapperPath, "dao");
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        projectPathSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPathBtnAction(textFieldProjectPath, "select a project package path");

                comboBoxClickAction(comboBoxControllerPath, "controller");
                comboBoxClickAction(comboBoxServicePath, "service");
                comboBoxClickAction(comboBoxModelPath, "model");
                comboBoxClickAction(comboBoxMapperPath, "dao");
            }

        });

        controllerPathSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPathBtnAction(comboBoxControllerPath, "select a controller path");
            }
        });

        servicePathSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPathBtnAction(comboBoxServicePath, "select a service path");
            }
        });

        modelPathSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPathBtnAction(comboBoxModelPath, "select a model path");
            }
        });

        mapperPathSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPathBtnAction(comboBoxMapperPath, "select a mapper path");
            }
        });

        // 表名改变监听
        Document document = textFieldTableName.getDocument();
//        document.addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                if (!CommonUtil.isNullOrEmpty(textFieldTableName.getText())) {
//                    textFieldSign.setText(CommonUtil.getSign(textFieldTableName.getText()));
//                }
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                if (!CommonUtil.isNullOrEmpty(textFieldTableName.getText())) {
//                    textFieldSign.setText(CommonUtil.getSign(textFieldTableName.getText()));
//                }
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                if (!CommonUtil.isNullOrEmpty(textFieldTableName.getText())) {
//                    textFieldSign.setText(CommonUtil.getSign(textFieldTableName.getText()));
//                }
//            }
//        });

        goButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean settingsConfigured = Settings.settingsConfigured();
                if (!settingsConfigured) {
                    Messages.showMessageDialog("please config env first", "tip", Messages.getInformationIcon());
                    return;
                }
                String checkResult = checkPath();
                if (checkResult != null) {
                    MessageUtil.showErrorMsg(checkResult);
                    return;
                }

                String tableName = textFieldTableName.getText();
                String projectPath = textFieldProjectPath.getText();
                String controllerPath = (String) comboBoxControllerPath.getSelectedItem();
                String servicePath = (String) comboBoxServicePath.getSelectedItem();
                String modelPath = (String) comboBoxModelPath.getSelectedItem();
                String mapperPath = (String) comboBoxMapperPath.getSelectedItem();

                propertiesComponent.setValue("Pyrange-WINDOWStableName", tableName);
                propertiesComponent.setValue("Pyrange-WINDOWSprojectPath", projectPath);
                propertiesComponent.setValue("Pyrange-WINDOWSmodelPath", modelPath);
                propertiesComponent.setValue("Pyrange-WINDOWScontrollerPath", controllerPath);
                propertiesComponent.setValue("Pyrange-WINDOWSservicePath", servicePath);
                propertiesComponent.setValue("Pyrange-WINDOWSmapperPath", mapperPath);
                propertiesComponent.setValue("Pyrange-WINDOWSmodelPath", modelPath);

                propertiesComponent.setValue("PYRNAGE-WINDOWS-controllerCheckBox", controllerCheckBox.isSelected());
                propertiesComponent.setValue("PYRNAGE-WINDOWS-serviceCheckBox", serviceCheckBox.isSelected());
                propertiesComponent.setValue("PYRNAGE-WINDOWS-mapperCheckBox", mapperCheckBox.isSelected());
                propertiesComponent.setValue("PYRNAGE-WINDOWS-modelCheckBox", modelCheckBox.isSelected());
                propertiesComponent.setValue("PYRNAGE-WINDOWS-frontEndCheckBox", frontEndCheckBox.isSelected());
                propertiesComponent.setValue("PYRNAGE-WINDOWS-testCheckBox", testCheckBox.isSelected());

                try {
                    CodeGenerate.generate(Settings.getBasicConfig(), getConfigModel());
                    MessageUtil.showSuccessMsg("Table " + tableName + " has been generated successfully!");
                } catch (PyrangeException e1) {
                    MessageUtil.showErrorMsg(e1.getMessage());
                    LOGGER.info(e1);
                } catch (Exception e1) {
                    MessageUtil.showErrorMsg(Throwables.getStackTraceAsString(e1));
                    LOGGER.info(e1);
                }

            }
        });

        viewControllerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean settingsConfigured = Settings.settingsConfigured();
                if (!settingsConfigured) {
                    Messages.showMessageDialog("请先初始化基本配置", "tip", Messages.getInformationIcon());
                    return;
                }

                try {
                    String controllerStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "controller.ftl");

                    CodingDialog codingDialog = new CodingDialog(controllerStr);
                    codingDialog.show();
                } catch (Exception ex) {
                    MessageUtil.showErrorMsg(Throwables.getStackTraceAsString(ex));
                    LOGGER.info(ex);
                }
            }
        });

        viewServiceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean settingsConfigured = Settings.settingsConfigured();
                if (!settingsConfigured) {
                    Messages.showMessageDialog("请先初始化基本配置", "tip", Messages.getInformationIcon());
                    return;
                }

                try {
                    String serviceImplStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "service-impl.ftl");
                    String serviceStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "service.ftl");

                    CodingDialog codingDialog = new CodingDialog(serviceImplStr + SEPARATOR_LINE + serviceStr);
                    codingDialog.show();
                } catch (Exception ex) {
                    MessageUtil.showErrorMsg(Throwables.getStackTraceAsString(ex));
                    LOGGER.info(ex);
                }
            }
        });

        viewModelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean settingsConfigured = Settings.settingsConfigured();
                if (!settingsConfigured) {
                    Messages.showMessageDialog("请先初始化基本配置", "tip", Messages.getInformationIcon());
                    return;
                }

                try {
                    String insertStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "model/insert.ftl");
                    String poStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "model/po.ftl");

                    CodingDialog codingDialog = new CodingDialog(poStr + SEPARATOR_LINE + insertStr);
                    codingDialog.show();
                } catch (Exception ex) {
                    MessageUtil.showErrorMsg(Throwables.getStackTraceAsString(ex));
                    LOGGER.info(ex);
                }
            }
        });


        viewMapperButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean settingsConfigured = Settings.settingsConfigured();
                if (!settingsConfigured) {
                    Messages.showMessageDialog("请先初始化基本配置", "tip", Messages.getInformationIcon());
                    return;
                }

                try {
                    String mapperStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "mapper.ftl");
                    String mapperXmlStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "mapperxml.ftl");

                    CodingDialog codingDialog = new CodingDialog(mapperXmlStr + SEPARATOR_LINE + mapperStr);
                    codingDialog.show();
                } catch (Exception ex) {
                    MessageUtil.showErrorMsg(Throwables.getStackTraceAsString(ex));
                    LOGGER.info(ex);
                }
            }
        });


        viewFEButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean settingsConfigured = Settings.settingsConfigured();
                if (!settingsConfigured) {
                    Messages.showMessageDialog("请先初始化基本配置", "tip", Messages.getInformationIcon());
                    return;
                }

                try {
                    String indexStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "fe/index.ftl");
                    String detailStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "fe/DetailDialog.ftl");
                    String editStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "fe/EditDialog.ftl");

                    CodingDialog codingDialog = new CodingDialog(
                            indexStr
                                    + SEPARATOR_LINE +
                                    detailStr
                                    + SEPARATOR_LINE +
                                    editStr);
                    codingDialog.show();
                } catch (Exception ex) {
                    MessageUtil.showErrorMsg(Throwables.getStackTraceAsString(ex));
                    LOGGER.info(ex);
                }
            }
        });

        viewTestButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean settingsConfigured = Settings.settingsConfigured();
                if (!settingsConfigured) {
                    Messages.showMessageDialog("请先初始化基本配置", "tip", Messages.getInformationIcon());
                    return;
                }

                try {
                    String testStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "test.ftl");
                    CodingDialog codingDialog = new CodingDialog(testStr);
                    codingDialog.show();
                } catch (Exception ex) {
                    MessageUtil.showErrorMsg(Throwables.getStackTraceAsString(ex));
                    LOGGER.info(ex);
                }

            }
        });

        settingLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Settings dialog = new Settings();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }


    private ConfigModel getConfigModel() {
        String projectPath = textFieldProjectPath.getText();
        String mapperPath = (String) comboBoxMapperPath.getSelectedItem();

//        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();

//        BasicConfig basicConfig = Settings.getBasicConfig();
        
        ConfigModel configModel = new ConfigModel();
//        configModel.setJdbcDatabase(basicConfig.getJdbcDatabase());
//        configModel.setJdbcUserName(basicConfig.getJdbcUserName());
//        configModel.setJdbcPassword(basicConfig.getJdbcPassword());
//        configModel.setGroupId(basicConfig.getGroupId());
//        configModel.setAuthor(basicConfig.getAuthor());
//        configModel.setJdkVersion(basicConfig.getJdkVersion());
//
//        configModel.setResultClassReference(basicConfig.getResultClassReference());
//        configModel.setResultClassName(basicConfig.getResultClassName());
//
//        configModel.setPageUtilClassReference(basicConfig.getPageClassReference());
//        configModel.setPageUtilClassName(basicConfig.getPageClassName());

        configModel.setProjectPath(projectPath);
        configModel.setControllerPath(CommonUtil.fomatPath((String) comboBoxControllerPath.getSelectedItem()));
        configModel.setServicePath(CommonUtil.fomatPath((String) comboBoxServicePath.getSelectedItem()));
        configModel.setModelPath(CommonUtil.fomatPath((String) comboBoxModelPath.getSelectedItem()));
        configModel.setMapperJavaPath(CommonUtil.fomatPath(mapperPath));
        configModel.setMapperXmlPath(CommonUtil.fomatPath(mapperPath));


        configModel.setGenerateModel(modelCheckBox.isSelected());
        configModel.setGenerateMapper(mapperCheckBox.isSelected());
        configModel.setGenerateController(controllerCheckBox.isSelected());
        configModel.setGenerateService(serviceCheckBox.isSelected());
        configModel.setGenerateFrontEnd(frontEndCheckBox.isSelected());
        configModel.setGenerateTest(testCheckBox.isSelected());

        if (samePathCheckBox.isSelected()) {
            configModel.setControllerPath(CommonUtil.fomatPath(projectPath));
            configModel.setServicePath(CommonUtil.fomatPath(projectPath));
            configModel.setModelPath(CommonUtil.fomatPath(projectPath));
            configModel.setMapperJavaPath(CommonUtil.fomatPath(projectPath));
            configModel.setMapperXmlPath(CommonUtil.fomatPath(projectPath));
        }

        configModel.setTableName(textFieldTableName.getText());
        return configModel;
    }

    private String checkPath() {
        if (CommonUtil.isNullOrEmpty(textFieldProjectPath.getText())) {
            return "ProjectPath required";
        }
        if (!samePathCheckBox.isSelected() && CommonUtil.isNullOrEmpty(comboBoxControllerPath.getSelectedItem())) {
            return "ControllerPath required";
        }
        if (!samePathCheckBox.isSelected() && CommonUtil.isNullOrEmpty(comboBoxServicePath.getSelectedItem())) {
            return "ServicePath required";
        }
        if (!samePathCheckBox.isSelected() && CommonUtil.isNullOrEmpty(comboBoxModelPath.getSelectedItem())) {
            return "ModelPath required";
        }
        if (!samePathCheckBox.isSelected() && CommonUtil.isNullOrEmpty(comboBoxMapperPath.getSelectedItem())) {
            return "MapperPath required";
        }
        return null;
    }

    private void selectPathBtnAction(JComboBox comboBox, String message) {
        String defaultFilePath;
        if (!CommonUtil.isNullOrEmpty(comboBox.getSelectedItem())) {
            defaultFilePath = (String) comboBox.getSelectedItem();
        } else if (!CommonUtil.isNullOrEmpty(textFieldProjectPath.getText())) {
            defaultFilePath = textFieldProjectPath.getText();
        } else {
            defaultFilePath = baseProjectPath;
        }
        JFileChooser jFileChooser = new JFileChooser(new File(defaultFilePath));
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setDialogTitle(message);
        jFileChooser.showDialog(new JLabel(), "select");
        File file = jFileChooser.getSelectedFile();
        if (file == null) {
            return;
        }
        comboBox.addItem(file.getAbsolutePath());
        comboBox.setSelectedItem(file.getAbsolutePath());
    }

    private void selectPathBtnAction(JTextField textField, String message) {
        String defaultFilePath;
        if (!CommonUtil.isNullOrEmpty(textField.getText())) {
            defaultFilePath = textField.getText();
        } else if (!CommonUtil.isNullOrEmpty(textFieldProjectPath.getText())) {
            defaultFilePath = textFieldProjectPath.getText();
        } else {
            defaultFilePath = baseProjectPath;
        }
        JFileChooser jFileChooser = new JFileChooser(new File(defaultFilePath));
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setDialogTitle(message);
        jFileChooser.showDialog(new JLabel(), "select");
        File file = jFileChooser.getSelectedFile();
        if (file == null) {
            return;
        }
        textField.setText(file.getAbsolutePath());
    }

    private void comboBoxClickAction(JComboBox comboBox, String key) {
        String projectPath = textFieldProjectPath.getText();
        if (CommonUtil.isNullOrEmpty(projectPath)) {
            MessageUtil.showErrorMsg("ProjectPath required");
            return;
        }
        List<File> directoryByProjectPath = CommonUtil.searchDirectory(projectPath, key);
        comboBox.removeAllItems();
        if (directoryByProjectPath != null && directoryByProjectPath.size() > 0) {
            directoryByProjectPath.forEach(file -> {
                comboBox.addItem(file.getAbsolutePath());
            });
            comboBox.setSelectedItem(directoryByProjectPath.get(0).getAbsolutePath());
        } else {
            MessageUtil.showErrorMsg("Not found " + key + " path");
        }
    }

}
