package com.pyrange.awesome.ui;

import com.intellij.ide.util.PropertiesComponent;
import com.pyrange.awesome.model.BasicConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldTableName;
    private JTextField textFieldTemplateName;
    private JLabel tableNameLabel;
    private JLabel templateNameLabel;

    public PopWindow() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // 设置窗口位置
        this.setLocation(600, 300);
        this.setPreferredSize(new Dimension(400, 200));

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


        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        textFieldTableName.setText(propertiesComponent.getValue("Pyrange-Windows-tableName"));
    }

    private void onOK() {
        CodingDialog codingDialog = new CodingDialog("controllerStr");
        codingDialog.show();
    }

    private void onCancel() {
        dispose();
    }

}
