package com.pyrange.awesome.generate;

import com.pyrange.awesome.model.TableInfo;
import com.pyrange.awesome.util.CommonUtil;
import com.pyrange.awesome.util.FreeMarkUtil;
import com.pyrange.awesome.model.ConfigModel;
import com.pyrange.awesome.model.ServiceGenerateInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-8-6
 **/
public class ServiceGenerate {
    private ConfigModel configModel;
    private TableInfo tableInfo;

    public ServiceGenerate(ConfigModel configModel, TableInfo tableInfo) {
        this.configModel = configModel;
        this.tableInfo = tableInfo;
    }

    public void generate() throws Exception{
        ServiceGenerateInfo serviceGenerateInfo = new ServiceGenerateInfo();
        serviceGenerateInfo.setAuthor(configModel.getAuthor());
        serviceGenerateInfo.setModuleName(CommonUtil.getNameUpperCamel(configModel.getSign()));
        serviceGenerateInfo.setModuleNameLower(CommonUtil.getNameLowerCamel(configModel.getSign()));
        serviceGenerateInfo.setBasePackage(CommonUtil.getPackageNameByPath(configModel.getServicePath()));
        serviceGenerateInfo.setModelPackage(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
        serviceGenerateInfo.setMapperPackage(CommonUtil.getPackageNameByPath(configModel.getMapperJavaPath()));
        serviceGenerateInfo.setServicePackage(CommonUtil.getPackageNameByPath(configModel.getServicePath()));
        serviceGenerateInfo.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
//        serviceGenerateInfo.setModelNameUpperCamel(CommonUtil.getNameUpperCamel(tableInfo.getTableName()));
//        serviceGenerateInfo.setModelNameLowerCamel(CommonUtil.getNameLowerCamel(tableInfo.getTableName()));
        serviceGenerateInfo.setTableComment(tableInfo.getTableComment());
//        serviceGenerateInfo.setTableName(tableInfo.getTableName());

        Map<String, Object> root = new HashMap<>(1);
        root.put("serviceGenerateInfo", serviceGenerateInfo);
        String serviceName = serviceGenerateInfo.getModuleName() + "Service.java";
        String serviceImplName = serviceGenerateInfo.getModuleName() + "ServiceImpl.java";
        FreeMarkUtil.generateFile(root, "service.ftl", configModel.getServicePath(), serviceName);
        FreeMarkUtil.generateFile(root, "service-impl.ftl", configModel.getServicePath() + "/impl", serviceImplName);
    }
}
