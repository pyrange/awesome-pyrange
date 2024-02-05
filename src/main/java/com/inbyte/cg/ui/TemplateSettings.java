package com.inbyte.cg.ui;

import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import com.inbyte.cg.model.BasicConfig;
import com.inbyte.cg.model.Result;
import com.inbyte.cg.util.TemplateUtil;
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

        // 
        this.setLocation(400, 200);
        BasicConfig basicConfig = BasicConfig.getBasicConfig();

        // 
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
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "fe/editDialog.ftl");
            }
        });

        feDetailButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditTemplateDialog(codeTemplatesBox.getSelectedItem().toString(), "fe/detailDialog.ftl");
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
         * 
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

                // 
                Result result = Settings.createNewTemplate(codeTemplateName);
                codeTemplatesBox.addItem(codeTemplateName);
                codeTemplatesBox.setSelectedItem(codeTemplateName);
                Settings.setSelectedCodeTemplate(codeTemplateName);

                if (result.failed()) {
                    Messages.showMessageDialog(result.getMsg(), "tip", Messages.getInformationIcon());
                }
            }
        });

        // initialDefaultTemplateButton
        initialDefaultTemplateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String codeTemplateName = Messages.showInputDialog("please input the template name to insure you want restore the default",
                        "restore to the default template",
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
                    Messages.showMessageDialog("the template name you input is not equal to the current", "tip", Messages.getInformationIcon());
                    return;
                }
                TemplateUtil.initialDefaultTemplate(codeTemplateName);
            }
        });
    }

    /**
     * checkTemplateName
     *
     * @param templateName
     * @return
     */
    private boolean checkTemplateName(String templateName) {
        if (StringUtils.isEmpty(templateName)) {
            return false;
        }
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~&]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(templateName);
        if (m.find()) {
            return false;
        }
        return true;
    }

    /**
     * 
     *
     * @param selectedCodeTemplate
     * @param templateName
     */
    private void openEditTemplateDialog(String selectedCodeTemplate, String templateName) {
        String templateStr = TemplateUtil.getTemplateContent(selectedCodeTemplate, templateName);
        TemplateEditDialog dialog = new TemplateEditDialog(templateStr, selectedCodeTemplate, templateName);
        dialog.pack();
        dialog.setVisible(true);
    }

    /**
     * ok
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
