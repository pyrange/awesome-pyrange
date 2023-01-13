package com.pyrange.awesome.generate;

import com.pyrange.awesome.model.*;
import com.pyrange.awesome.util.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试代码生成
 *
 * @author : chenjw
 * @date: 2023-1-13
 **/
public class CodeGenerate {

    public static void generate(ConfigModel configModel) throws Exception {
        // 获取处理配置信息
        TableInfo tableInfo = getTableInfo(configModel);
        GenerateInfo generateInfo = getGenerateInfo(configModel, tableInfo);

        if (configModel.getGenerateModel()) {
            ModelGenerate.generate(configModel, generateInfo);
        }
        if (configModel.getGenerateMapper()) {
            MapperGenerate.generate(configModel, generateInfo);
        }
        if (configModel.getGenerateController()) {
            ControllerGenerate.generate(configModel, generateInfo);
        }
        if (configModel.getGenerateService()) {
            ServiceGenerate.generate(configModel, generateInfo);
        }
        if (configModel.getGenerateFrontEnd()) {
            FrontEndGenerate.generate(configModel, generateInfo);
        }
        if (configModel.getGenerateTest()) {
            TestGenerate.generate(configModel, generateInfo);
        }
    }

    public static String getGeneratedModelStr(ConfigModel configModel, String template) throws Exception {
        TableInfo tableInfo = getTableInfo(configModel);
        GenerateInfo generateInfo = getGenerateInfo(configModel, tableInfo);
        return FreeMarkUtil.getFileStr(configModel, generateInfo, template) ;
    }

    private static TableInfo getTableInfo(ConfigModel configModel) throws Exception {
        TableUtil tableUtil = new TableUtil(configModel.getJdbcHost(),
                configModel.getJdbcDatabase(),
                configModel.getJdbcUserName(),
                configModel.getJdbcPassword());
        TableInfo tableInfo = tableUtil.getTableInfo(configModel.getTableName());
        if (tableInfo == null) {
            throw new Exception("Table is not exist!");
        }
        if (tableInfo.getTableColumns() == null) {
            throw new Exception("Table column is empty!");
        }
        String priKey = null;
        for (TableColumn tableColumn : tableInfo.getTableColumns()) {
            if (tableColumn.isPrimaryKey()) {
                priKey = tableColumn.getColumnName();
            }
        }
        if (CommonUtil.isNullOrEmpty(priKey)) {
            throw new Exception("Table has not primaryKey. Model generated!");
        }
        return tableInfo;
    }

    private static GenerateInfo getGenerateInfo(ConfigModel configModel, TableInfo tableInfo) {
        String moduleName = CommonUtil.getNameUpperCamel(configModel.getTableName());

        GenerateInfo generateInfo = new GenerateInfo();
        generateInfo.setAuthor(configModel.getAuthor());
        generateInfo.setModuleName(moduleName);
        generateInfo.setModuleNameUppercaseCamel(moduleName);
        generateInfo.setModuleNameLowercase(moduleName.toLowerCase());
        generateInfo.setModuleNameWithDot(CommonUtil.str2LowercaseWithDot(tableInfo.getTableName()));
        generateInfo.setModuleNameWithSlash(CommonUtil.str2LowercaseWithSlash(tableInfo.getTableName()));

        generateInfo.setModelNameLowercase(CommonUtil.getNameLowercase(tableInfo.getTableName()));
        generateInfo.setModelNameUpperCamel(CommonUtil.getNameUpperCamel(tableInfo.getTableName()));
        generateInfo.setModelNameLowerCamel(CommonUtil.getNameLowerCamel(tableInfo.getTableName()));

        generateInfo.setControllerPackage(CommonUtil.getPackageNameByPath(configModel.getControllerPath()));
        generateInfo.setControllerPackageWithSlash(generateInfo.getControllerPackage().replace(".", "/"));
        generateInfo.setServicePackage(CommonUtil.getPackageNameByPath(configModel.getServicePath()));
        generateInfo.setModelPackage(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
        generateInfo.setMapperPackage(CommonUtil.getPackageNameByPath(configModel.getMapperJavaPath()));

        generateInfo.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        generateInfo.setTableComment(tableInfo.getTableComment());
        generateInfo.setTableName(tableInfo.getTableName());
        List<GenerateColumnInfo> generateColumnInfos = new ArrayList<>();
        List<String> importList = new ArrayList<>();
        for (TableColumn tableColumn : tableInfo.getTableColumns()) {
            GenerateColumnInfo generateColumnInfo = new GenerateColumnInfo();
            String javaTypeName = DataTypeEnum.getJavaTypeNameByDataType(tableColumn.getDataType());
            generateColumnInfo.setColumnName(SqlReservedWords.containsWord(tableColumn.getColumnName()) ? "`" + tableColumn.getColumnName() + "`" : tableColumn.getColumnName());
            generateColumnInfo.setColumnCamelName(CommonUtil.getNameLowerCamel(tableColumn.getColumnName()));
            generateColumnInfo.setColumnUpperCamelName(CommonUtil.getNameUpperCamel(tableColumn.getColumnName()));
            generateColumnInfo.setGetterName("get" + CommonUtil.getNameUpperCamel(tableColumn.getColumnName()));
            generateColumnInfo.setSetterName("set" + CommonUtil.getNameUpperCamel(tableColumn.getColumnName()));
            generateColumnInfo.setColumnComment(tableColumn.getColumnComment());
            generateColumnInfo.setColumnJavaTypeName(javaTypeName);
            generateColumnInfo.setColumnJdbcType(DataTypeEnum.getJdbcTypeByDataType(tableColumn.getDataType()));
            generateColumnInfo.setNullable(tableColumn.getNullable());
            generateColumnInfo.setCharacterMaximumLength(tableColumn.getCharacterMaximumLength());
            generateColumnInfos.add(generateColumnInfo);

            String columnJavaTypeName = DataTypeEnum.getJavaTypeByDataType(tableColumn.getDataType());
            if (CommonUtil.isNeedImport(javaTypeName) && !importList.contains(columnJavaTypeName)) {
                importList.add(columnJavaTypeName);
            }
            if (tableColumn.isPrimaryKey()) {
                generateInfo.setPrimaryKey(SqlReservedWords.containsWord(tableColumn.getColumnName()) ? "`" + tableColumn.getColumnName() + "`" : tableColumn.getColumnName());
                generateInfo.setPrimaryKeyCamel(CommonUtil.getNameLowerCamel(tableColumn.getColumnName()));
                generateInfo.setPrimaryKeyJdbcType(DataTypeEnum.getJdbcTypeByDataType(tableColumn.getDataType()));
                generateInfo.setPrimaryKeyJavaTypeName(DataTypeEnum.getJavaTypeNameByDataType(tableColumn.getDataType()));
                generateInfo.setPrimaryKeyJavaType(DataTypeEnum.getJdbcTypeByDataType(tableColumn.getDataType()));
            }
        }
        generateInfo.setColumnList(generateColumnInfos);
        generateInfo.setImportList(importList);

        return generateInfo;
    }

}
