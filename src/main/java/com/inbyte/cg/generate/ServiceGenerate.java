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
