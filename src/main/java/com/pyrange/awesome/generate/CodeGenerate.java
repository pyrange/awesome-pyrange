package com.pyrange.awesome.generate;

import com.pyrange.awesome.model.TableInfo;
import com.pyrange.awesome.util.CommonUtil;
import com.pyrange.awesome.util.TableUtil;
import com.pyrange.awesome.model.ConfigModel;
import com.pyrange.awesome.model.TableColumn;

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
            new ModelGenerate(configModel, tableInfo).generate();
            throw new Exception("Table has not primaryKey. Model generated!");
        }

        if (configModel.getGenerateModel()) {
            new ModelGenerate(configModel, tableInfo).generate();
        }
        if (configModel.getGenerateMapper()) {
            new MapperGenerate(configModel, tableInfo).generate();
        }
        if (configModel.getGenerateControllerService()) {
            new ControllerGenerate(configModel, tableInfo).generate();
            new ServiceGenerate(configModel, tableInfo).generate();
        }
        if (configModel.getGenerateFrontEnd()) {
            new FrontEndGenerate(configModel, tableInfo).generate();
        }
    }
}
