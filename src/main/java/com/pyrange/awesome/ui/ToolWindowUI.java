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
    private JButton fePathSelectButton;

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
    private JComboBox comboBoxFePath;


    private String baseProjectPath;

    public JPanel getToolWindowContent() {
        return this.toolWindowContent;
    }

    private static final Logger LOGGER = Logger.getInstance(ToolWindowUI.class);

    public ToolWindowUI(Project project) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();

        this.baseProjectPath = project.getBasePath();
        // 初始化checkbox
        controllerCheckBox.setSelected(propertiesComponent.getBoolean("Pyrange-Windows-controllerCheckBox", true));
        serviceCheckBox.setSelected(propertiesComponent.getBoolean("Pyrange-Windows-serviceCheckBox", true));
        mapperCheckBox.setSelected(propertiesComponent.getBoolean("Pyrange-Windows-mapperCheckBox", true));
        modelCheckBox.setSelected(propertiesComponent.getBoolean("Pyrange-Windows-modelCheckBox", true));
        testCheckBox.setSelected(propertiesComponent.getBoolean("Pyrange-Windows-testCheckBox", true));
        frontEndCheckBox.setSelected(propertiesComponent.getBoolean("Pyrange-Windows-frontEndCheckBox", true));

        textFieldTableName.setText(propertiesComponent.getValue("Pyrange-Windows-tableName"));
        textFieldProjectPath.setText(propertiesComponent.getValue("Pyrange-Windows-projectPath"));

        comboBoxControllerPath.addItem(propertiesComponent.getValue("Pyrange-Windows-controllerPath"));
        comboBoxControllerPath.setSelectedItem(propertiesComponent.getValue("Pyrange-Windows-controllerPath"));

        comboBoxServicePath.addItem(propertiesComponent.getValue("Pyrange-Windows-servicePath"));
        comboBoxServicePath.setSelectedItem(propertiesComponent.getValue("Pyrange-Windows-servicePath"));

        comboBoxModelPath.addItem(propertiesComponent.getValue("Pyrange-Windows-modelPath"));
        comboBoxModelPath.setSelectedItem(propertiesComponent.getValue("Pyrange-Windows-modelPath"));

        comboBoxMapperPath.addItem(propertiesComponent.getValue("Pyrange-Windows-mapperPath"));
        comboBoxMapperPath.setSelectedItem(propertiesComponent.getValue("Pyrange-Windows-mapperPath"));

        comboBoxFePath.addItem(propertiesComponent.getValue("Pyrange-Windows-fePath"));
        comboBoxFePath.setSelectedItem(propertiesComponent.getValue("Pyrange-Windows-fePath"));
        
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
                setPathAsProject(comboBoxFePath);
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

        fePathSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPathBtnAction(comboBoxFePath, "select a fe path");
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
                String fePath = (String) comboBoxFePath.getSelectedItem();

                propertiesComponent.setValue("Pyrange-Windows-tableName", tableName);
                propertiesComponent.setValue("Pyrange-Windows-projectPath", projectPath);
                propertiesComponent.setValue("Pyrange-Windows-modelPath", modelPath);
                propertiesComponent.setValue("Pyrange-Windows-controllerPath", controllerPath);
                propertiesComponent.setValue("Pyrange-Windows-servicePath", servicePath);
                propertiesComponent.setValue("Pyrange-Windows-mapperPath", mapperPath);
                propertiesComponent.setValue("Pyrange-Windows-modelPath", modelPath);
                propertiesComponent.setValue("Pyrange-Windows-fePath", fePath);

                propertiesComponent.setValue("Pyrange-Windows-controllerCheckBox", controllerCheckBox.isSelected());
                propertiesComponent.setValue("Pyrange-Windows-serviceCheckBox", serviceCheckBox.isSelected());
                propertiesComponent.setValue("Pyrange-Windows-mapperCheckBox", mapperCheckBox.isSelected());
                propertiesComponent.setValue("Pyrange-Windows-modelCheckBox", modelCheckBox.isSelected());
                propertiesComponent.setValue("Pyrange-Windows-frontEndCheckBox", frontEndCheckBox.isSelected());
                propertiesComponent.setValue("Pyrange-Windows-testCheckBox", testCheckBox.isSelected());

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
                    Messages.showMessageDialog("please set the basic settings first", "tip", Messages.getInformationIcon());
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
                    String addStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "fe/addDrawer.ftl");
                    String detailStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "fe/detailDialog.ftl");
                    String editStr = CodeGenerate.getGeneratedModelStr(Settings.getBasicConfig(), getConfigModel(), "fe/editDialog.ftl");

                    CodingDialog codingDialog = new CodingDialog(
                            indexStr
                                    + SEPARATOR_LINE +
                                    addStr
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
        configModel.setFePath(CommonUtil.fomatPath((String) comboBoxFePath.getSelectedItem()));


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
            configModel.setFePath(CommonUtil.fomatPath(projectPath));
        }

        configModel.setTableName(textFieldTableName.getText());
        return configModel;
    }

    private String checkPath() {
        if (samePathCheckBox.isSelected() && CommonUtil.isNullOrEmpty(textFieldProjectPath.getText())) {
            return "ProjectPath required";
        }
        if (controllerCheckBox.isSelected() && CommonUtil.isNullOrEmpty(comboBoxControllerPath.getSelectedItem())) {
            return "ControllerPath required";
        }
        if (serviceCheckBox.isSelected() && CommonUtil.isNullOrEmpty(comboBoxServicePath.getSelectedItem())) {
            return "ServicePath required";
        }
        if (modelCheckBox.isSelected() && CommonUtil.isNullOrEmpty(comboBoxModelPath.getSelectedItem())) {
            return "ModelPath required";
        }
        if (mapperCheckBox.isSelected() && CommonUtil.isNullOrEmpty(comboBoxMapperPath.getSelectedItem())) {
            return "MapperPath required";
        }
        if (frontEndCheckBox.isSelected() && CommonUtil.isNullOrEmpty(comboBoxFePath.getSelectedItem())) {
            return "FePath required";
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
            MessageUtil.showErrorMsg("Didn't found " + key + " path");
        }
    }

    private void setPathAsProject(JComboBox comboBox) {
        String projectPath = textFieldProjectPath.getText();
        if (CommonUtil.isNullOrEmpty(projectPath)) {
            MessageUtil.showErrorMsg("ProjectPath required");
            return;
        }
        comboBox.removeAllItems();
        comboBox.addItem(projectPath);
    }

    private void createUIComponents() {
    }
}
