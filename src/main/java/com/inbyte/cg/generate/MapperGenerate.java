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
 * @date: 2018-6-12
 **/
public class MapperGenerate {


    public static void generate(BasicConfig basicConfig, ConfigModel configModel, GenerateInfo generateInfo) throws Exception {
        Map<String, Object> root = new HashMap<>(3);
        root.put("generateInfo", generateInfo);
        root.put("configModel", configModel);
        root.put("basicConfig", basicConfig);

        String mapperFileName = generateInfo.getModuleName() + "Mapper.java";
        String mapperXmlFileName = generateInfo.getModuleName() + "Mapper.xml";
        FreeMarkUtil.generateFileByTemplateContent(root, "mapper.ftl", configModel.getMapperJavaPath(), mapperFileName);
        FreeMarkUtil.generateFileByTemplateContent(root, "mapperxml.ftl", configModel.getMapperXmlPath(), mapperXmlFileName);
    }
}
