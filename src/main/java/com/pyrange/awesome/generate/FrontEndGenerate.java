package com.pyrange.awesome.generate;

import com.pyrange.awesome.model.*;
import com.pyrange.awesome.util.CommonUtil;
import com.pyrange.awesome.util.DataTypeEnum;
import com.pyrange.awesome.util.FreeMarkUtil;
import com.pyrange.awesome.util.SqlReservedWords;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这里是类描述
 *
 * @author : chenjw
 * @date: 2022-12-22
 **/
public class FrontEndGenerate {
    private ConfigModel configModel;
    private TableInfo tableInfo;

    public FrontEndGenerate(ConfigModel configModel, TableInfo tableInfo) {
        this.configModel = configModel;
        this.tableInfo = tableInfo;
    }

    public void generate() throws Exception {
        FrontEndGenerateInfo frontEndGenerateInfo = new FrontEndGenerateInfo();
        frontEndGenerateInfo.setAuthor(configModel.getAuthor());
        frontEndGenerateInfo.setModuleName(CommonUtil.getNameUpperCamel(configModel.getSign()));
        frontEndGenerateInfo.setModuleNameLower(CommonUtil.getNameLowerCamel(configModel.getSign()));
        frontEndGenerateInfo.setBasePackage(CommonUtil.getPackageNameByPath(configModel.getControllerPath()));
        frontEndGenerateInfo.setServicePackage(CommonUtil.getPackageNameByPath(configModel.getServicePath()));
        frontEndGenerateInfo.setModelPackage(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
        frontEndGenerateInfo.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
//        frontEndGenerateInfo.setModelNameUpperCamel(CommonUtil.getNameUpperCamel(tableInfo.getTableName()));
//        frontEndGenerateInfo.setModelNameLowerCamel(CommonUtil.getNameLowerCamel(tableInfo.getTableName()));
//        frontEndGenerateInfo.setQueryObjectNameLowerCamel(CommonUtil.getNameLowerCamel(configModel.getSign())+"Query");
        frontEndGenerateInfo.setTableComment(tableInfo.getTableComment());
//        frontEndGenerateInfo.setTableName(tableInfo.getTableName());
        frontEndGenerateInfo.setBaseRequestMapping(configModel.getSign());

        List<GenerateColumnInfo> generateColumnInfos = new ArrayList<>();
        for (TableColumn tableColumn : tableInfo.getTableColumns()) {
            GenerateColumnInfo generateColumnInfo = new GenerateColumnInfo();
            String javaTypeName = DataTypeEnum.getJavaTypeNameByDataType(tableColumn.getDataType());
            generateColumnInfo.setColumnComment(tableColumn.getColumnComment());
            generateColumnInfo.setColumnJavaTypeName(javaTypeName);
            generateColumnInfo.setColumnCamelName(CommonUtil.getNameLowerCamel(tableColumn.getColumnName()));
            generateColumnInfo.setNullable(tableColumn.getNullable());
            generateColumnInfo.setCharacterMaximumLength(tableColumn.getCharacterMaximumLength());
            generateColumnInfos.add(generateColumnInfo);

            if (tableColumn.isPrimaryKey()) {
                String primaryKey = SqlReservedWords.containsWord(tableColumn.getColumnName())
                        ? "`" + tableColumn.getColumnName() + "`"
                        : tableColumn.getColumnName();
                frontEndGenerateInfo.setPrimaryKey(primaryKey);
                frontEndGenerateInfo.setPrimaryKeyLowerCamel(CommonUtil.getNameLowerCamel(primaryKey));
            }
        }
        frontEndGenerateInfo.setColumnList(generateColumnInfos);

        Map<String, Object> root = new HashMap<>(1);
        root.put("frontEndGenerateInfo", frontEndGenerateInfo);
        String indexFileName = frontEndGenerateInfo.getModuleName() + ".vue";
        FreeMarkUtil.generateFile(root, "fe/index.ftl",
                configModel.getProjectPath() + "/" + configModel.getSign(), indexFileName);

        String detailFileName = frontEndGenerateInfo.getModuleName() + "DetailDialog.vue";
        FreeMarkUtil.generateFile(root, "fe/DetailDialog.ftl",
                configModel.getProjectPath() + "/" + configModel.getSign() + "/component/", detailFileName);

        String editFileName = frontEndGenerateInfo.getModuleName() + "EditDialog.vue";
        FreeMarkUtil.generateFile(root, "fe/EditDialog.ftl",
                configModel.getProjectPath() + "/" + configModel.getSign() + "/component/", editFileName);
    }
}
