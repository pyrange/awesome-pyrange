package com.pyrange.awesome.ui;

import com.pyrange.awesome.model.BasicConfig;
import com.pyrange.awesome.util.FreeMarkUtil;

import javax.swing.*;
import java.awt.event.*;

public class TemplateSettings extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox codeTemplatesBox;
    private JButton controllerButton;
    private JButton serviceButton;
    private JButton serviceImplButton;
    private JButton mapperButton;
    private JButton mapperXmlButton;
    private JButton insertButton;
    private JButton updateButton;
    private JButton briefButton;
    private JButton POButton;
    private JButton queryButton;
    private JButton testButton;
    private JButton indexButton;
    private JButton feDetailButton;
    private JButton editButton;
    private JButton ModelDetailButton;
    private JButton addNewTemplateButton;

    public TemplateSettings() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
                String templateStr = FreeMarkUtil.getTemplateStr();
                TemplateEditDialog dialog = new TemplateEditDialog(templateStr);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    private void openEditTemplateDialog(String template) {

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        TemplateSettings dialog = new TemplateSettings();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
