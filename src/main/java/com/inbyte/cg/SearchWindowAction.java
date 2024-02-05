package com.inbyte.cg;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.inbyte.cg.ui.PopWindow;
import org.jetbrains.annotations.NotNull;

public class SearchWindowAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PopWindow codingDialog = new PopWindow();
        codingDialog.pack();
        codingDialog.setVisible(true);
    }
}
