//package com.pyrange.awesome.config;
//
//import com.intellij.openapi.project.Project;
//import com.intellij.openapi.wm.ToolWindow;
//import com.intellij.openapi.wm.ToolWindowFactory;
//import com.intellij.ui.content.Content;
//import com.intellij.ui.content.ContentFactory;
//import com.pyrange.awesome.ui.ToolWindowUI;
//import org.jetbrains.annotations.NotNull;
//
//public class ToolWindowConfig implements ToolWindowFactory{
//    @Override
//    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
//        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
//        Content content = contentFactory.createContent(new ToolWindowUI().getToolWindowContent(), "", false);
//        toolWindow.getContentManager().addContent(content);
//    }
//}
