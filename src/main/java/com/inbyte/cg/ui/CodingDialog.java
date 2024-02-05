package com.inbyte.cg.ui;

import com.intellij.openapi.ui.DialogWrapper;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;

public class CodingDialog extends DialogWrapper {

    private final String content;

    public CodingDialog(String content) {
        super(true);
        this.content = content;
        init();
        setTitle("Pyrange Code");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {

        JPanel dialogPanel = new JPanel(new BorderLayout());
        dialogPanel.setPreferredSize(new Dimension(1000, 700));

        JTextArea jTextArea = new JTextArea(content);
        jTextArea.setText(content);
        jTextArea.setLineWrap(true);
        jTextArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(jTextArea);
        //
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dialogPanel.add(scrollPane);

        return dialogPanel;
    }

}