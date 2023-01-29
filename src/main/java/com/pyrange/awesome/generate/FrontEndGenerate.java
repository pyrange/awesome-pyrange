package com.pyrange.awesome.generate;

import com.pyrange.awesome.model.BasicConfig;
import com.pyrange.awesome.model.ConfigModel;
import com.pyrange.awesome.model.GenerateInfo;
import com.pyrange.awesome.util.FreeMarkUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Vue 前端代码生成
 *
 * @author : chenjw
 * @date: 2023-1-13
 **/
public class FrontEndGenerate {

    public static void generate(BasicConfig basicConfig, ConfigModel configModel, GenerateInfo generateInfo) throws Exception {
        Map<String, Object> root = new HashMap<>(3);
        root.put("generateInfo", generateInfo);
        root.put("configModel", configModel);
        root.put("basicConfig", basicConfig);

        FreeMarkUtil.generateFileByTemplateContent(root, basicConfig.getSelectedCodeTemplate(), "fe/index.ftl",
                configModel.getProjectPath() + "/" + generateInfo.getModuleName(), "index.vue");

        FreeMarkUtil.generateFileByTemplateContent(root, basicConfig.getSelectedCodeTemplate(), "fe/DetailDialog.ftl",
                configModel.getProjectPath() + "/" + generateInfo.getModuleName() + "/component/", "DetailDialog.vue");

        FreeMarkUtil.generateFileByTemplateContent(root, basicConfig.getSelectedCodeTemplate(), "fe/EditDialog.ftl",
                configModel.getProjectPath() + "/" + generateInfo.getModuleName() + "/component/", "EditDialog.vue");
    }
}
