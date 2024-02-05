package com.inbyte.cg.generate;

import com.inbyte.cg.model.BasicConfig;
import com.inbyte.cg.model.ConfigModel;
import com.inbyte.cg.model.GenerateInfo;
import com.inbyte.cg.util.FreeMarkUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author : chenjw
 * @date: 2023-1-13
 **/
public class TestGenerate {

    public static void generate(BasicConfig basicConfig, ConfigModel configModel, GenerateInfo generateInfo) throws Exception {
        Map<String, Object> root = new HashMap<>(3);
        root.put("generateInfo", generateInfo);
        root.put("configModel", configModel);
        root.put("basicConfig", basicConfig);

        String testConstantPath = configModel.getProjectPath().replace("\\", "/")
                + "/src/test/java/"
                + basicConfig.getGroupId().replace(".", "/")
                + "/test/";
        File file = new File(testConstantPath + "TestConstant.java");
        if (!file.exists()) {
            FreeMarkUtil.generateFileByTemplateContent(root, 
                    "test-constant.ftl", testConstantPath, "TestConstant.java");
        }

        FreeMarkUtil.generateFileByTemplateContent(root, "test.ftl",
                configModel.getProjectPath() + "/src/test/java/" + generateInfo.getControllerPackageWithSlash(),
                generateInfo.getModuleName() + "Test.java");
    }
}
