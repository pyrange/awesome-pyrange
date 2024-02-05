package com.inbyte.cg.generate;

import com.inbyte.cg.model.BasicConfig;
import com.inbyte.cg.model.ConfigModel;
import com.inbyte.cg.model.GenerateInfo;
import com.inbyte.cg.util.FreeMarkUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 
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
        FreeMarkUtil.generateFileByTemplateContent(root, "controller.ftl", configModel.getControllerPath(), fileName);
    }
}
