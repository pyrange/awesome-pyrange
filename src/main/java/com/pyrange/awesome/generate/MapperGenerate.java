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
 * @date: 2018-6-12
 **/
public class MapperGenerate {


    public static void generate(ConfigModel configModel, GenerateInfo generateInfo) throws Exception {
        Map<String, Object> root = new HashMap<>(2);
        root.put("generateInfo", generateInfo);
        root.put("configModel", configModel);

        String mapperFileName = generateInfo.getModuleName() + "Mapper.java";
        String mapperXmlFileName = generateInfo.getModuleName() + "Mapper.xml";
        FreeMarkUtil.generateFile(root, "mapper.ftl", configModel.getMapperJavaPath(), mapperFileName);
        FreeMarkUtil.generateFile(root, "mapperxml.ftl", configModel.getMapperXmlPath(), mapperXmlFileName);
    }
}
