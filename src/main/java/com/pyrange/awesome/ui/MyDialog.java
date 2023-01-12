package com.pyrange.awesome.ui;

import com.intellij.openapi.ui.DialogWrapper;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;

public class MyDialog extends DialogWrapper {

    private String content;

    public MyDialog(String content) {
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

        // 设置自动换行
        // 定义带滚动条的panel，并将JTextArea存入到panel中，使textarea具有滚动条显示功能。
        JScrollPane scrollPane = new JScrollPane(jTextArea);
        //取消显示水平滚动条
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //显示垂直滚动条
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dialogPanel.add(scrollPane);

        return dialogPanel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}