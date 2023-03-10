package com.pyrange.awesome.generate;

import com.pyrange.awesome.model.BasicConfig;
import com.pyrange.awesome.model.ConfigModel;
import com.pyrange.awesome.model.GenerateInfo;
import com.pyrange.awesome.util.FreeMarkUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试代码生成
 *
 * @author : chenjw
 * @date: 2023-1-13
 **/
public class ServiceGenerate {

    public static void generate(BasicConfig basicConfig, ConfigModel configModel, GenerateInfo generateInfo) throws Exception {
        Map<String, Object> root = new HashMap<>(3);
        root.put("generateInfo", generateInfo);
        root.put("configModel", configModel);
        root.put("basicConfig", basicConfig);

        String serviceName = generateInfo.getModuleName() + "Service.java";
        String serviceImplName = generateInfo.getModuleName() + "ServiceImpl.java";
        FreeMarkUtil.generateFileByTemplateContent(root, "service.ftl", configModel.getServicePath(), serviceName);
        FreeMarkUtil.generateFileByTemplateContent(root, "service-impl.ftl", configModel.getServicePath() + "/impl", serviceImplName);
    }
}
