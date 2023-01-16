package com.pyrange.awesome.generate;

import com.pyrange.awesome.model.BasicConfig;
import com.pyrange.awesome.model.ConfigModel;
import com.pyrange.awesome.model.GenerateInfo;
import com.pyrange.awesome.util.FreeMarkUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class ControllerGenerate {

    public static void generate(BasicConfig basicConfig, ConfigModel configModel, GenerateInfo generateInfo) throws Exception {
        Map<String, Object> root = new HashMap<>(3);
        root.put("generateInfo", generateInfo);
        root.put("configModel", configModel);
        root.put("basicConfig", basicConfig);

        String fileName = generateInfo.getModelNameUpperCamel() + "Controller.java";
        FreeMarkUtil.generateFile(root, "controller.ftl", configModel.getControllerPath(), fileName);
    }
}
