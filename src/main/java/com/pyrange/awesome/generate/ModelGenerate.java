package com.pyrange.awesome.generate;

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
public class ModelGenerate {


    public static void generate(ConfigModel configModel, GenerateInfo generateInfo) throws Exception {

        Map<String, Object> root = new HashMap<>(1);
        root.put("generateInfo", generateInfo);

        FreeMarkUtil.generateFile(root, "model/po.ftl",
                configModel.getModelPath() + "/" + generateInfo.getModuleNameWithSlash(),
                generateInfo.getModuleName() + "Po.java");

        FreeMarkUtil.generateFile(root, "model/query.ftl",
                configModel.getModelPath() + "/" + generateInfo.getModuleNameWithSlash(),
                generateInfo.getModuleName() + "Query.java");

        FreeMarkUtil.generateFile(root, "model/update.ftl",
                configModel.getModelPath() + "/" + generateInfo.getModuleNameWithSlash(),
                generateInfo.getModuleName() + "Update.java");

        FreeMarkUtil.generateFile(root, "model/insert.ftl",
                configModel.getModelPath() + "/" + generateInfo.getModuleNameWithSlash(),
                generateInfo.getModuleName() + "Insert.java");

        FreeMarkUtil.generateFile(root, "model/detail.ftl",
                configModel.getModelPath() + "/" + generateInfo.getModuleNameWithSlash(),
                generateInfo.getModuleName() + "Detail.java");

        FreeMarkUtil.generateFile(root, "model/brief.ftl",
                configModel.getModelPath() + "/" + generateInfo.getModuleNameWithSlash(),
                generateInfo.getModuleName() + "Brief.java");
    }
}
