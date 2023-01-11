package ${generateInfo.basePackage};

import ${generateInfo.modelPath}.${generateInfo.modelNameLowerCamel}.${generateInfo.moduleName}Po;
import ${generateInfo.modelPath}.${generateInfo.modelNameLowerCamel}.${generateInfo.moduleName}Query;
import ${generateInfo.modelPath}.${generateInfo.modelNameLowerCamel}.${generateInfo.moduleName}Brief;
import ${generateInfo.modelPath}.${generateInfo.modelNameLowerCamel}.${generateInfo.moduleName}Detail;
import java.util.List;

/**
 * ${generateInfo.tableComment}
 *
 * 表名：  ${generateInfo.tableName}
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 */
public interface ${generateInfo.moduleName}Mapper {

    /**
     * 全字段新增
     *
     * @param insert
     * @return int 新增条数
     **/
    int insert(${generateInfo.moduleName}Po insert);

    /**
     * 根据主键动态修改
     *
     * @param update
     * @return int 修改条数
     **/
    int update(${generateInfo.moduleName}Po update);

    /**
     * 概要
     *
     * @param id
     * @return ${generateInfo.moduleName}
     **/
    ${generateInfo.moduleName}Detail brief(${generateInfo.primaryKeyJavaTypeName} id);

    /**
     * 详情
     *
     * @param id
     * @return ${generateInfo.moduleName}
     **/
    ${generateInfo.moduleName}Detail detail(${generateInfo.primaryKeyJavaTypeName} id);

    /**
     * 查询列表
     *
     * @return ${generateInfo.moduleName}
     **/
    List<${generateInfo.moduleName}${"Brief>"} list(${generateInfo.moduleName}Query query);
}