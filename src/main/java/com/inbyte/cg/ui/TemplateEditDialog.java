package com.inbyte.cg.ui;

import com.intellij.openapi.ui.Messages;
import com.inbyte.cg.util.TemplateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemplateEditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea templateTextArea;
    private String selectedCodeTemplate;
    private String templateName;

    public TemplateEditDialog(String templateStr, String selectedCodeTemplate, String templateName) {
        setContentPane(contentPane);

        this.selectedCodeTemplate = selectedCodeTemplate;
        this.templateName = templateName;

        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setPreferredSize(new Dimension(1000, 700));

        templateTextArea.setText(templateStr);
        templateTextArea.setCaretPosition(0);
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
    }

    private void onOK() {
        TemplateUtil.saveTemplate(selectedCodeTemplate, templateName, templateTextArea.getText());
        Messages.showMessageDialog("", "tip", Messages.getInformationIcon());
        dispose();
    }

    private void onCancel() {
        dispose();
    }

}
