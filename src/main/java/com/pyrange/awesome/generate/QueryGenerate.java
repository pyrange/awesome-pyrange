package com.pyrange.awesome.generate;//package com.yn.code.generate;
//
//import com.yn.code.model.ConfigModel;
//import com.yn.code.model.ControllerGenerateInfo;
//import com.yn.code.model.TableInfo;
//import com.yn.code.util.CommonUtil;
//import com.yn.code.util.FreeMarkUtil;
////
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Query參數生成
// *
// * @author : chenjw
// * @date: 2021-8-3
// **/
//public class QueryGenerate {
//    private ConfigModel configModel;
//    private TableInfo tableInfo;
//
//    public QueryGenerate(ConfigModel configModel, TableInfo tableInfo) {
//        this.configModel = configModel;
//        this.tableInfo = tableInfo;
//    }
//
//    public void generate() throws Exception {
//        ControllerGenerateInfo queryGenerateInfo = new ControllerGenerateInfo();
//        queryGenerateInfo.setAuthor(configModel.getAuthor());
//        queryGenerateInfo.setBasePackage(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
//        queryGenerateInfo.setDate(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date()));
//        queryGenerateInfo.setModuleName(CommonUtil.getNameUpperCamel(configModel.getSign()));
//        queryGenerateInfo.setTableComment(tableInfo.getTableComment());
//
//        Map<String, Object> root = new HashMap<>(1);
//        root.put("queryGenerateInfo", queryGenerateInfo);
//        FreeMarkUtil.generateFile(root, "model/query.ftl", configModel.getModelPath() + "/param/",
//                CommonUtil.getNameUpperCamel(configModel.getSign()) + "Query.java");
//        FreeMarkUtil.generateFile(root, "model/update.ftl", configModel.getModelPath() + "/param/",
//                CommonUtil.getNameUpperCamel(configModel.getSign()) + "Update.java");
//        FreeMarkUtil.generateFile(root, "model/insert.ftl", configModel.getModelPath() + "/param/",
//                CommonUtil.getNameUpperCamel(configModel.getSign()) + "Insert.java");
//        FreeMarkUtil.generateFile(root, "model/detail.ftl", configModel.getModelPath() + "/dto/",
//                CommonUtil.getNameUpperCamel(configModel.getSign()) + "Detail.java");
//        FreeMarkUtil.generateFile(root, "model/brief.ftl", configModel.getModelPath() + "/dto/",
//                CommonUtil.getNameUpperCamel(configModel.getSign()) + "Brief.java");
//    }
//}
