package com.inbyte.cg.generate;

import com.inbyte.cg.model.BasicConfig;
import com.inbyte.cg.model.ConfigModel;
import com.inbyte.cg.model.GenerateInfo;
import com.inbyte.cg.util.FreeMarkUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Vue 
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

        FreeMarkUtil.generateFileByTemplateContent(root, "fe/index.ftl",
                configModel.getFePath() + "/" + generateInfo.getModuleNameWithHyphen(), "index.vue");

        FreeMarkUtil.generateFileByTemplateContent(root, "fe/addDrawer.ftl",
                configModel.getFePath() + "/" + generateInfo.getModuleNameWithHyphen() + "/component/", "addDrawer.vue");

        FreeMarkUtil.generateFileByTemplateContent(root, "fe/detailDialog.ftl",
                configModel.getFePath() + "/" + generateInfo.getModuleNameWithHyphen() + "/component/", "detailDialog.vue");

        FreeMarkUtil.generateFileByTemplateContent(root, "fe/editDialog.ftl",
                configModel.getFePath() + "/" + generateInfo.getModuleNameWithHyphen() + "/component/", "editDialog.vue");
    }
}
