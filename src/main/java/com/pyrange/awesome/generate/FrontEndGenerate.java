package com.pyrange.awesome.generate;

import com.pyrange.awesome.model.ConfigModel;
import com.pyrange.awesome.model.GenerateInfo;
import com.pyrange.awesome.util.FreeMarkUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里是类描述
 *
 * @author : chenjw
 * @date: 2022-12-22
 **/
public class FrontEndGenerate {

    public static void generate(ConfigModel configModel, GenerateInfo generateInfo) throws Exception {
        Map<String, Object> root = new HashMap<>(1);
        root.put("generateInfo", generateInfo);
        String indexFileName = generateInfo.getModuleName() + ".vue";
        FreeMarkUtil.generateFile(root, "fe/index.ftl",
                configModel.getProjectPath() + "/" + configModel.getSign(), indexFileName);

        String detailFileName = generateInfo.getModuleName() + "DetailDialog.vue";
        FreeMarkUtil.generateFile(root, "fe/DetailDialog.ftl",
                configModel.getProjectPath() + "/" + configModel.getSign() + "/component/", detailFileName);

        String editFileName = generateInfo.getModuleName() + "EditDialog.vue";
        FreeMarkUtil.generateFile(root, "fe/EditDialog.ftl",
                configModel.getProjectPath() + "/" + configModel.getSign() + "/component/", editFileName);
    }
}
