package com.pyrange.awesome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormLayoutDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormLayoutDemo());
    }

    public FormLayoutDemo() {
        JFrame frame = new JFrame("Form Layout Demo");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(5);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理提交按钮点击事件
                String name = nameField.getText();
                String age = ageField.getText();
                System.out.println("Name: " + name + ", Age: " + age);
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(submitButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
