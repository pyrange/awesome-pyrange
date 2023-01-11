package com.pyrange.awesome.generate;

import com.pyrange.awesome.model.*;
import com.pyrange.awesome.util.CommonUtil;
import com.pyrange.awesome.util.DataTypeEnum;
import com.pyrange.awesome.util.FreeMarkUtil;
import com.pyrange.awesome.util.SqlReservedWords;
import com.yn.code.model.*;
import com.yn.code.util.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-12
 **/
public class MapperGenerate {
    private ConfigModel configModel;
    private TableInfo tableInfo;

    public MapperGenerate(ConfigModel configModel, TableInfo tableInfo) {
        this.configModel = configModel;
        this.tableInfo = tableInfo;
    }

    public void generate() throws Exception{
        MapperGenerateInfo mapperGenerateInfo = new MapperGenerateInfo();
        mapperGenerateInfo.setAuthor(configModel.getAuthor());
        mapperGenerateInfo.setModuleName(CommonUtil.getNameUpperCamel(configModel.getSign()));
        mapperGenerateInfo.setBasePackage(CommonUtil.getPackageNameByPath(configModel.getMapperJavaPath()));
        mapperGenerateInfo.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        mapperGenerateInfo.setModelNameUpperCamel(CommonUtil.getNameUpperCamel(tableInfo.getTableName()));
        mapperGenerateInfo.setModelNameLowerCamel(CommonUtil.getNameLowerCamel(tableInfo.getTableName()));
        mapperGenerateInfo.setTableComment(tableInfo.getTableComment());
        mapperGenerateInfo.setTableName(tableInfo.getTableName());
        mapperGenerateInfo.setModelPath(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
        List<MapperGenerateColumnInfo> mapperGenerateColumnInfos = new ArrayList<>();
        for (TableColumn tableColumn : tableInfo.getTableColumns()) {
            MapperGenerateColumnInfo mapperGenerateColumnInfo = new MapperGenerateColumnInfo();
            mapperGenerateColumnInfo.setColumnComment(tableColumn.getColumnComment());
            mapperGenerateColumnInfo.setColumnJavaTypeName(DataTypeEnum.getJavaTypeNameByDataType(tableColumn.getDataType()));
            mapperGenerateColumnInfo.setColumnCamelName(CommonUtil.getNameLowerCamel(tableColumn.getColumnName()));
            mapperGenerateColumnInfo.setColumnName(SqlReservedWords.containsWord(tableColumn.getColumnName()) ? "`"+ tableColumn.getColumnName() +"`" : tableColumn.getColumnName());
            mapperGenerateColumnInfo.setColumnJdbcType(DataTypeEnum.getJdbcTypeByDataType(tableColumn.getDataType()));
            mapperGenerateColumnInfos.add(mapperGenerateColumnInfo);
            if(tableColumn.isPrimaryKey()){
                mapperGenerateInfo.setPrimaryKey(SqlReservedWords.containsWord(tableColumn.getColumnName()) ? "`"+ tableColumn.getColumnName() +"`" : tableColumn.getColumnName());
                mapperGenerateInfo.setPrimaryKeyCamel(CommonUtil.getNameLowerCamel(tableColumn.getColumnName()));
                mapperGenerateInfo.setPrimaryKeyJdbcType(DataTypeEnum.getJdbcTypeByDataType(tableColumn.getDataType()));
                mapperGenerateInfo.setPrimaryKeyJavaTypeName(DataTypeEnum.getJavaTypeNameByDataType(tableColumn.getDataType()));
                mapperGenerateInfo.setPrimaryKeyJavaType(DataTypeEnum.getJdbcTypeByDataType(tableColumn.getDataType()));
            }
        }
        mapperGenerateInfo.setColumnList(mapperGenerateColumnInfos);
        Map<String, Object> root = new HashMap<>(1);
        root.put("mapperGenerateInfo", mapperGenerateInfo);
        String mapperFileName = mapperGenerateInfo.getModuleName() + "Mapper.java";
        String mapperXmlFileName = mapperGenerateInfo.getModuleName() + "Mapper.xml";
        FreeMarkUtil.generateFile(root, "mapper.ftl", configModel.getMapperJavaPath(), mapperFileName);
        FreeMarkUtil.generateFile(root, "mapperxml.ftl", configModel.getMapperXmlPath(), mapperXmlFileName);
    }
}
