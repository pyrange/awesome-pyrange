package com.pyrange.awesome.ui;

import com.google.common.base.Throwables;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.pyrange.awesome.util.TableUtil;
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

    private JTextField textFieldHost;
    private JTextField textFieldUserName;
    private JPasswordField textFieldPassword;
    private JTextField textFieldTableName;
    private JTextField textFieldAuthor;
    private JTextField textFieldProjectPath;

    private JComboBox comboBoxDatabase;
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

    private JCheckBox modelCheckBox;
    private JCheckBox mapperCheckBox;
    private JCheckBox controllerServiceCheckBox;
    private JCheckBox frontEndCheckBox;
    private JCheckBox samePathCheckBox;
    private JButton viewModelButton;
    private JButton viewMapperButton;
    private JButton viewFEButton;


    private String baseProjectPath;

    public JPanel getToolWindowContent() {
        return this.toolWindowContent;
    }

    private static final Logger LOGGER = Logger.getInstance(ToolWindowUI.class);

    private NotificationGroup myBatisCode_notification_group = new NotificationGroup("Awesome-Pyrange",
            NotificationDisplayType.BALLOON, true);


    public ToolWindowUI(Project project) {
        this.baseProjectPath = project.getBasePath();
        // 初始化checkbox
        modelCheckBox.setSelected(true);
        mapperCheckBox.setSelected(true);
        controllerServiceCheckBox.setSelected(true);

        PropertiesComponent propertiesComponentProject = PropertiesComponent.getInstance(project);
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();

        textFieldHost.setText(propertiesComponent.getValue("jdbcHost"));
        textFieldUserName.setText(propertiesComponent.getValue("jdbcUserName"));
        textFieldPassword.setText(propertiesComponent.getValue("jdbcPassword"));
        textFieldTableName.setText(propertiesComponent.getValue("tableName"));
        textFieldAuthor.setText(propertiesComponent.getValue("author"));
        comboBoxDatabase.addItem(propertiesComponent.getValue("jdbcDatabase"));
        comboBoxDatabase.setSelectedItem(propertiesComponent.getValue("jdbcDatabase"));
        textFieldProjectPath.setText(propertiesComponentProject.getValue("projectPath"));

        comboBoxControllerPath.addItem(propertiesComponentProject.getValue("controllerPath"));
        comboBoxControllerPath.setSelectedItem(propertiesComponentProject.getValue("controllerPath"));

        comboBoxServicePath.addItem(propertiesComponentProject.getValue("servicePath"));
        comboBoxServicePath.setSelectedItem(propertiesComponentProject.getValue("servicePath"));

        comboBoxModelPath.addItem(propertiesComponentProject.getValue("modelPath"));
        comboBoxModelPath.setSelectedItem(propertiesComponentProject.getValue("modelPath"));

        comboBoxMapperPath.addItem(propertiesComponentProject.getValue("mapperPath"));
        comboBoxMapperPath.setSelectedItem(propertiesComponentProject.getValue("mapperPath"));

        comboBoxDatabase.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                String jdbcHost = textFieldHost.getText();
                String jdbcUserName = textFieldUserName.getText();
                String jdbcPassword = textFieldPassword.getText();
                if (CommonUtil.isNullOrEmpty(jdbcHost)) {
                    showErrorMsg("Host:Port required");
                    return;
                }
                if (CommonUtil.isNullOrEmpty(jdbcUserName)) {
                    showErrorMsg("UserName required");
                    return;
                }
                if (CommonUtil.isNullOrEmpty(jdbcPassword)) {
                    showErrorMsg("Password required");
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
                    showErrorMsg(Throwables.getStackTraceAsString(e1));
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
                String checkResult = checkParam();
                if (checkResult != null) {
                    showErrorMsg(checkResult);
                    return;
                }

                String jdbcHost = textFieldHost.getText();
                String jdbcUserName = textFieldUserName.getText();
                String jdbcPassword = textFieldPassword.getText();
                String jdbcDatabase = (String) comboBoxDatabase.getSelectedItem();
                String tableName = textFieldTableName.getText();
                String projectPath = textFieldProjectPath.getText();
                String controllerPath = (String) comboBoxControllerPath.getSelectedItem();
                String servicePath = (String) comboBoxServicePath.getSelectedItem();
                String modelPath = (String) comboBoxModelPath.getSelectedItem();
                String mapperPath = (String) comboBoxMapperPath.getSelectedItem();
                String author = textFieldAuthor.getText();

                propertiesComponent.setValue("jdbcHost", jdbcHost);
                propertiesComponent.setValue("jdbcDatabase", jdbcDatabase);
                propertiesComponent.setValue("jdbcUserName", jdbcUserName);
                propertiesComponent.setValue("jdbcPassword", jdbcPassword);
                propertiesComponent.setValue("tableName", tableName);
//                propertiesComponent.setValue("sign", sign);
                propertiesComponentProject.setValue("projectPath", projectPath);
                propertiesComponentProject.setValue("modelPath", modelPath);
                propertiesComponentProject.setValue("controllerPath", controllerPath);
                propertiesComponentProject.setValue("servicePath", servicePath);
                propertiesComponentProject.setValue("mapperPath", mapperPath);
                propertiesComponentProject.setValue("modelPath", modelPath);
                propertiesComponent.setValue("author", author);

                try {
                    CodeGenerate.generate(getConfigModel());
                    showSuccessMsg("Table " + tableName + " has been generated successfully!");
                } catch (Exception e1) {
                    showErrorMsg(Throwables.getStackTraceAsString(e1));
                    LOGGER.info(e1);
                }

            }
        });

        viewModelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String checkResult = checkParam();
                if (checkResult != null) {
                    showErrorMsg(checkResult);
                    return;
                }

                try {
                    String insertStr = CodeGenerate.getGeneratedModelStr(getConfigModel(), "model/insert.ftl");
                    String poStr = CodeGenerate.getGeneratedModelStr(getConfigModel(), "model/po.ftl");

                    MyDialog myDialog = new MyDialog(poStr + SEPARATOR_LINE + insertStr);
                    myDialog.show();
                } catch (Exception ex) {
                    showErrorMsg(Throwables.getStackTraceAsString(ex));
                    LOGGER.info(ex);
                }

            }
        });


        viewMapperButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String checkResult = checkParam();
                if (checkResult != null) {
                    showErrorMsg(checkResult);
                    return;
                }

                try {
                    String mapperStr = CodeGenerate.getGeneratedModelStr(getConfigModel(), "mapper.ftl");
                    String mapperXmlStr = CodeGenerate.getGeneratedModelStr(getConfigModel(), "mapperxml.ftl");

                    MyDialog myDialog = new MyDialog(mapperXmlStr + SEPARATOR_LINE + mapperStr);
                    myDialog.show();
                } catch (Exception ex) {
                    showErrorMsg(Throwables.getStackTraceAsString(ex));
                    LOGGER.info(ex);
                }

            }
        });


        viewFEButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String checkResult = checkParam();
                if (checkResult != null) {
                    showErrorMsg(checkResult);
                    return;
                }

                try {
                    String indexStr = CodeGenerate.getGeneratedModelStr(getConfigModel(), "fe/index.ftl");
                    String detailStr = CodeGenerate.getGeneratedModelStr(getConfigModel(), "fe/DetailDialog.ftl");
                    String editStr = CodeGenerate.getGeneratedModelStr(getConfigModel(), "fe/EditDialog.ftl");

                    MyDialog myDialog = new MyDialog(
                            indexStr
                                    + SEPARATOR_LINE +
                                    detailStr
                                    + SEPARATOR_LINE +
                                    editStr);
                    myDialog.show();
                } catch (Exception ex) {
                    showErrorMsg(Throwables.getStackTraceAsString(ex));
                    LOGGER.info(ex);
                }

            }
        });
    }

    private ConfigModel getConfigModel() {
        String projectPath = textFieldProjectPath.getText();
        String mapperPath = (String) comboBoxMapperPath.getSelectedItem();

        ConfigModel configModel = new ConfigModel();
        configModel.setJdbcHost(textFieldHost.getText());
        configModel.setJdbcDatabase((String) comboBoxDatabase.getSelectedItem());
        configModel.setJdbcUserName(textFieldUserName.getText());
        configModel.setJdbcPassword(textFieldPassword.getText());
        configModel.setProjectPath(projectPath);
        configModel.setControllerPath(CommonUtil.fomatPath((String) comboBoxControllerPath.getSelectedItem()));
        configModel.setServicePath(CommonUtil.fomatPath((String) comboBoxServicePath.getSelectedItem()));
        configModel.setModelPath(CommonUtil.fomatPath((String) comboBoxModelPath.getSelectedItem()));
        configModel.setMapperJavaPath(CommonUtil.fomatPath(mapperPath));
        configModel.setMapperXmlPath(CommonUtil.fomatPath(mapperPath));
        configModel.setAuthor(textFieldAuthor.getText());
        configModel.setGenerateModel(modelCheckBox.isSelected());
        configModel.setGenerateMapper(mapperCheckBox.isSelected());
        configModel.setGenerateControllerService(controllerServiceCheckBox.isSelected());
        configModel.setGenerateFrontEnd(frontEndCheckBox.isSelected());

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

    private String checkParam() {
        if (CommonUtil.isNullOrEmpty(textFieldHost.getText())) {
            return "Host:Port required";
        }
        if (CommonUtil.isNullOrEmpty(textFieldUserName.getText())) {
            return "UserName required";
        }
        if (textFieldPassword.getPassword().length == 0) {
            return "Password required";
        }
        if (CommonUtil.isNullOrEmpty(comboBoxDatabase.getSelectedItem())) {
            return "Database required";
        }
        if (CommonUtil.isNullOrEmpty(textFieldTableName.getText())) {
            return "TableName required";
        }
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
        if (CommonUtil.isNullOrEmpty(textFieldAuthor.getText())) {
            return "author required";
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
            showErrorMsg("ProjectPath required");
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
            showErrorMsg("Not found " + key + " path");
        }
    }

    private void showSuccessMsg(String msg) {
        Notification notification = myBatisCode_notification_group.createNotification("Awesome-Pyrange message", msg, NotificationType.INFORMATION, null);
        Notifications.Bus.notify(notification);
    }

    private void showErrorMsg(String msg) {
        Notification notification = myBatisCode_notification_group.createNotification("Awesome-Pyrange Error Message", msg, NotificationType.ERROR, null);
        Notifications.Bus.notify(notification);
    }
}
