package com.pyrange.awesome.generate;

import com.pyrange.awesome.model.*;
import com.pyrange.awesome.util.CommonUtil;
import com.pyrange.awesome.util.DataTypeEnum;
import com.pyrange.awesome.util.SqlReservedWords;
import com.pyrange.awesome.util.TableUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class CodeGenerate {
    public void generate(ConfigModel configModel) throws Exception {
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

        // 获取处理配置信息
        GenerateInfo generateInfo = getGenerateInfo(configModel, tableInfo);

        if (configModel.getGenerateModel()) {
            ModelGenerate.generate(configModel, generateInfo);
        }
        if (configModel.getGenerateMapper()) {
            MapperGenerate.generate(configModel, generateInfo);
        }
        if (configModel.getGenerateControllerService()) {
            new ControllerGenerate(configModel, tableInfo).generate();
            new ServiceGenerate(configModel, tableInfo).generate();
        }
        if (configModel.getGenerateFrontEnd()) {
            new FrontEndGenerate(configModel, tableInfo).generate();
        }
    }

    private GenerateInfo getGenerateInfo(ConfigModel configModel, TableInfo tableInfo) {
        String moduleName = CommonUtil.getNameUpperCamel(configModel.getSign());

        GenerateInfo generateInfo = new GenerateInfo();
        generateInfo.setAuthor(configModel.getAuthor());
        generateInfo.setModuleName(moduleName);

        generateInfo.setModelPath(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
        generateInfo.setModelNameLowercase(CommonUtil.getNameLowercase(tableInfo.getTableName()));
        generateInfo.setModelNameUpperCamel(CommonUtil.getNameUpperCamel(tableInfo.getTableName()));
        generateInfo.setModelNameLowerCamel(CommonUtil.getNameLowerCamel(tableInfo.getTableName()));


        generateInfo.setBasePackage(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
        generateInfo.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        generateInfo.setTableComment(tableInfo.getTableComment());
        generateInfo.setTableName(tableInfo.getTableName());
        List<GenerateColumnInfo> generateColumnInfos = new ArrayList<>();
        List<String> importList = new ArrayList<>();
        for (TableColumn tableColumn : tableInfo.getTableColumns()) {
            GenerateColumnInfo generateColumnInfo = new GenerateColumnInfo();
            String javaTypeName = DataTypeEnum.getJavaTypeNameByDataType(tableColumn.getDataType());
            generateColumnInfo.setColumnName(SqlReservedWords.containsWord(tableColumn.getColumnName()) ? "`"+ tableColumn.getColumnName() +"`" : tableColumn.getColumnName());
            generateColumnInfo.setColumnComment(tableColumn.getColumnComment());
            generateColumnInfo.setColumnJavaTypeName(javaTypeName);
            generateColumnInfo.setColumnJdbcType(DataTypeEnum.getJdbcTypeByDataType(tableColumn.getDataType()));
            generateColumnInfo.setColumnCamelName(CommonUtil.getNameLowerCamel(tableColumn.getColumnName()));
            generateColumnInfo.setNullable(tableColumn.getNullable());
            generateColumnInfo.setCharacterMaximumLength(tableColumn.getCharacterMaximumLength());
            generateColumnInfos.add(generateColumnInfo);

            String columnJavaTypeName = DataTypeEnum.getJavaTypeByDataType(tableColumn.getDataType());
            if (CommonUtil.isNeedImport(javaTypeName) && !importList.contains(columnJavaTypeName)) {
                importList.add(columnJavaTypeName);
                generateInfo.setPrimaryKey(SqlReservedWords.containsWord(tableColumn.getColumnName()) ? "`"+ tableColumn.getColumnName() +"`" : tableColumn.getColumnName());
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

//        MapperGenerateInfo mapperGenerateInfo = new MapperGenerateInfo();
//        mapperGenerateInfo.setAuthor(configModel.getAuthor());
//        mapperGenerateInfo.setModuleName(CommonUtil.getNameUpperCamel(configModel.getSign()));
//        mapperGenerateInfo.setBasePackage(CommonUtil.getPackageNameByPath(configModel.getMapperJavaPath()));
//        mapperGenerateInfo.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
//        mapperGenerateInfo.setModelNameUpperCamel(CommonUtil.getNameUpperCamel(tableInfo.getTableName()));
//        mapperGenerateInfo.setModelNameLowerCamel(CommonUtil.getNameLowerCamel(tableInfo.getTableName()));
//        mapperGenerateInfo.setTableComment(tableInfo.getTableComment());
//        mapperGenerateInfo.setTableName(tableInfo.getTableName());
//        mapperGenerateInfo.setModelPath(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
//        List<MapperGenerateColumnInfo> mapperGenerateColumnInfos = new ArrayList<>();
//        for (TableColumn tableColumn : tableInfo.getTableColumns()) {
//            MapperGenerateColumnInfo mapperGenerateColumnInfo = new MapperGenerateColumnInfo();
//            mapperGenerateColumnInfo.setColumnComment(tableColumn.getColumnComment());
//            mapperGenerateColumnInfo.setColumnJavaTypeName(DataTypeEnum.getJavaTypeNameByDataType(tableColumn.getDataType()));
//            mapperGenerateColumnInfo.setColumnCamelName(CommonUtil.getNameLowerCamel(tableColumn.getColumnName()));
//            mapperGenerateColumnInfo.setColumnName(SqlReservedWords.containsWord(tableColumn.getColumnName()) ? "`"+ tableColumn.getColumnName() +"`" : tableColumn.getColumnName());
//            mapperGenerateColumnInfo.setColumnJdbcType(DataTypeEnum.getJdbcTypeByDataType(tableColumn.getDataType()));
//            mapperGenerateColumnInfos.add(mapperGenerateColumnInfo);
//            if(tableColumn.isPrimaryKey()){
//                mapperGenerateInfo.setPrimaryKey(SqlReservedWords.containsWord(tableColumn.getColumnName()) ? "`"+ tableColumn.getColumnName() +"`" : tableColumn.getColumnName());
//                mapperGenerateInfo.setPrimaryKeyCamel(CommonUtil.getNameLowerCamel(tableColumn.getColumnName()));
//                mapperGenerateInfo.setPrimaryKeyJdbcType(DataTypeEnum.getJdbcTypeByDataType(tableColumn.getDataType()));
//                mapperGenerateInfo.setPrimaryKeyJavaTypeName(DataTypeEnum.getJavaTypeNameByDataType(tableColumn.getDataType()));
//                mapperGenerateInfo.setPrimaryKeyJavaType(DataTypeEnum.getJdbcTypeByDataType(tableColumn.getDataType()));
//            }
//        }
//        mapperGenerateInfo.setColumnList(mapperGenerateColumnInfos);
}
