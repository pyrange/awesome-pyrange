package com.pyrange.awesome.generate;

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

    public static void generate(ConfigModel configModel, GenerateInfo generateInfo) throws Exception {
        Map<String, Object> root = new HashMap<>(2);
        root.put("generateInfo", generateInfo);
        root.put("configModel", configModel);

        String serviceName = generateInfo.getModuleName() + "Service.java";
        String serviceImplName = generateInfo.getModuleName() + "ServiceImpl.java";
        FreeMarkUtil.generateFile(root, "service.ftl", configModel.getServicePath(), serviceName);
        FreeMarkUtil.generateFile(root, "service-impl.ftl", configModel.getServicePath() + "/impl", serviceImplName);
    }
}
