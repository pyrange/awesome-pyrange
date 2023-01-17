package com.pyrange.awesome.ui;

import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import com.pyrange.awesome.model.BasicConfig;
import com.pyrange.awesome.model.Result;
import com.pyrange.awesome.util.FreeMarkUtil;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateSettings extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox codeTemplatesBox;
    private JComboBox parentCodeTemplatesBox;
    private JButton controllerButton;
    private JButton serviceButton;
    private JButton serviceImplButton;
    private JButton mapperButton;
    private JButton mapperXmlButton;
    private JButton insertButton;
    private JButton updateButton;
    private JButton briefButton;
    private JButton poButton;
    private JButton queryButton;
    private JButton testButton;
    private JButton feIndexButton;
    private JButton feDetailButton;
    private JButton editButton;
    private JButton addNewTemplateButton;
    private JButton modelDetailButton;
    private JButton constantButton;
    private JButton initialDefaultTemplateButton;


    public TemplateSettings(JComboBox parentCodeTemplatesBox) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.parentCodeTemplatesBox = parentCodeTemplatesBox;

        // 设置窗口位置
        this.setLocation(400, 200);//设置窗口居中显示
        BasicConfig basicConfig = Settings.getBasicConfig();

        // 模板设置
        for (String template : basicConfig.getCodeTemplates()) {
            codeTemplatesBox.addItem(template);
        }
        codeTemplatesBox.setSelectedItem(basicConfig.getSelectedCodeTemplate());


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


        controllerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "controller.ftl");
            }
        });

        serviceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "service.ftl");
            }
        });

        serviceImplButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "service-impl.ftl");
            }
        });

        mapperButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "mapper.ftl");
            }
        });

        mapperXmlButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "mapperxml.ftl");
            }
        });

        insertButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "model/insert.ftl");
            }
        });

        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "model/update.ftl");
            }
        });

        poButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "model/po.ftl");
            }
        });

        queryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "model/query.ftl");
            }
        });

        briefButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "model/brief.ftl");
            }
        });

        modelDetailButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "model/detail.ftl");
            }
        });

        feIndexButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "fe/index.ftl");
            }
        });

        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "fe/EditDialog.ftl");
            }
        });

        feDetailButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "fe/DetailDialog.ftl");
            }
        });

        testButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "test.ftl");
            }
        });

        constantButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "test-constant.ftl");
            }
        });

        /**
         * 创建新的模板
         */
        addNewTemplateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                Messages.InputDialog.
                String codeTemplateName = Messages.showInputDialog("template name", "please input the new template name",
                        Messages.getInformationIcon(),
                        "",
                        new InputValidator() {
                            @Override
                            public boolean checkInput(String inputString) {
                                return checkTemplateName(inputString);
                            }

                            @Override
                            public boolean canClose(String inputString) {
                                return checkTemplateName(inputString);
                            }
                        });
                if (StringUtils.isEmpty(codeTemplateName)) {
                    return;
                }

                // 创建模板
                Result result = Settings.createNewTemplate(codeTemplateName);
                codeTemplatesBox.addItem(codeTemplateName);
                codeTemplatesBox.setSelectedItem(codeTemplateName);
                Settings.setSelectedCodeTemplate(codeTemplateName);

                if (result.failed()) {
                    Messages.showMessageDialog(result.getMsg(), "tip", Messages.getInformationIcon());
                }
            }
        });

        // 初始化默认模板配置
        initialDefaultTemplateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String codeTemplateName = Messages.showInputDialog("请输入模板名称确认初始化模板配置", "恢复初始模板配置",
                        Messages.getInformationIcon(),
                        "",
                        new InputValidator() {
                            @Override
                            public boolean checkInput(String inputString) {
                                return checkTemplateName(inputString);
                            }

                            @Override
                            public boolean canClose(String inputString) {
                                return checkTemplateName(inputString);
                            }
                        });
                if (StringUtils.isEmpty(codeTemplateName)) {
                    return;
                }
                if (!codeTemplateName.equals(codeTemplatesBox.getSelectedItem().toString())) {
                    Messages.showMessageDialog("输入模板集名称不正确", "tip", Messages.getInformationIcon());
                    return;
                }
                FreeMarkUtil.initialDefaultTemplate(codeTemplateName);
            }
        });
    }

    /**
     * 检查模板名称
     *
     * @param templateName
     * @return
     */
    private boolean checkTemplateName(String templateName) {
        if (StringUtils.isEmpty(templateName)) {
            return false;
        }
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(templateName);
        if (m.find()) {
            return false;
        }
        return true;
    }

    /**
     * 打开模板编辑页面
     *
     * @param selectedCodeTemplate
     * @param templateName
     */
    private void openEditTemplateDialog(String selectedCodeTemplate, String templateName) {
        String templateStr = FreeMarkUtil.getTemplateContent(selectedCodeTemplate, templateName);
        TemplateEditDialog dialog = new TemplateEditDialog(templateStr, selectedCodeTemplate, templateName);
        dialog.pack();
        dialog.setVisible(true);
    }

    /**
     * ok按钮操作
     */
    private void onOK() {
        int itemCount = codeTemplatesBox.getItemCount();
        parentCodeTemplatesBox.removeAllItems();
        for (int i = 0; i < itemCount; i++) {
            parentCodeTemplatesBox.addItem(codeTemplatesBox.getItemAt(i));
        }
        parentCodeTemplatesBox.setSelectedItem(codeTemplatesBox.getSelectedItem());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
