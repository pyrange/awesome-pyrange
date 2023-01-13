package com.pyrange.awesome.generate;

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

    public static void generate(ConfigModel configModel, GenerateInfo generateInfo) throws Exception {
        Map<String, Object> root = new HashMap<>(2);
        root.put("generateInfo", generateInfo);
        root.put("configModel", configModel);

        String indexFileName = generateInfo.getModuleName() + ".vue";
        FreeMarkUtil.generateFile(root, "fe/index.ftl",
                configModel.getProjectPath() + "/" + generateInfo.getModuleName(), indexFileName);

        String detailFileName = generateInfo.getModuleName() + "DetailDialog.vue";
        FreeMarkUtil.generateFile(root, "fe/DetailDialog.ftl",
                configModel.getProjectPath() + "/" + generateInfo.getModuleName() + "/component/", detailFileName);

        String editFileName = generateInfo.getModuleName() + "EditDialog.vue";
        FreeMarkUtil.generateFile(root, "fe/EditDialog.ftl",
                configModel.getProjectPath() + "/" + generateInfo.getModuleName() + "/component/", editFileName);
    }
}
