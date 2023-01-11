package com.pyrange.awesome.generate;

import com.pyrange.awesome.model.TableInfo;
import com.pyrange.awesome.util.CommonUtil;
import com.pyrange.awesome.util.FreeMarkUtil;
import com.pyrange.awesome.model.ConfigModel;
import com.pyrange.awesome.model.ControllerGenerateInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class ControllerGenerate {
    private ConfigModel configModel;
    private TableInfo tableInfo;

    public ControllerGenerate(ConfigModel configModel, TableInfo tableInfo) {
        this.configModel = configModel;
        this.tableInfo = tableInfo;
    }

    public void generate() throws Exception{
        ControllerGenerateInfo controllerGenerateInfo = new ControllerGenerateInfo();
        controllerGenerateInfo.setAuthor(configModel.getAuthor());
        controllerGenerateInfo.setModuleName(CommonUtil.getNameUpperCamel(configModel.getSign()));
        controllerGenerateInfo.setModuleNameLower(CommonUtil.getNameLowerCamel(configModel.getSign()));
        controllerGenerateInfo.setBasePackage(CommonUtil.getPackageNameByPath(configModel.getControllerPath()));
        controllerGenerateInfo.setServicePackage(CommonUtil.getPackageNameByPath(configModel.getServicePath()));
        controllerGenerateInfo.setModelPackage(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
        controllerGenerateInfo.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
//        controllerGenerateInfo.setModelNameUpperCamel(CommonUtil.getNameUpperCamel(tableInfo.getTableName()));
//        controllerGenerateInfo.setModelNameLowerCamel(CommonUtil.getNameLowerCamel(tableInfo.getTableName()));
//        controllerGenerateInfo.setQueryObjectNameLowerCamel(CommonUtil.getNameLowerCamel(configModel.getSign())+"Query");
        controllerGenerateInfo.setTableComment(tableInfo.getTableComment());
//        controllerGenerateInfo.setTableName(tableInfo.getTableName());
        controllerGenerateInfo.setBaseRequestMapping(configModel.getSign());

        Map<String, Object> root = new HashMap<>(1);
        root.put("controllerGenerateInfo", controllerGenerateInfo);
        String fileName = controllerGenerateInfo.getModuleName() + "Controller.java";
        FreeMarkUtil.generateFile(root, "controller.ftl", configModel.getControllerPath(), fileName);
    }
}
