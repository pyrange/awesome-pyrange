package ${mapperGenerateInfo.basePackage};

import ${mapperGenerateInfo.modelPath}.${mapperGenerateInfo.modelNameLowerCamel}.${mapperGenerateInfo.moduleName}Po;
import ${mapperGenerateInfo.modelPath}.${mapperGenerateInfo.modelNameLowerCamel}.${mapperGenerateInfo.moduleName}Query;
import ${mapperGenerateInfo.modelPath}.${mapperGenerateInfo.modelNameLowerCamel}.${mapperGenerateInfo.moduleName}Brief;
import ${mapperGenerateInfo.modelPath}.${mapperGenerateInfo.modelNameLowerCamel}.${mapperGenerateInfo.moduleName}Detail;
import java.util.List;

/**
 * ${mapperGenerateInfo.tableComment}
 *
 * 表名：  ${mapperGenerateInfo.tableName}
 * @author ${mapperGenerateInfo.author}
 * @date ${mapperGenerateInfo.date}
 */
public interface ${mapperGenerateInfo.moduleName}Mapper {

    /**
     * 全字段新增
     *
     * @param insert
     * @return int 新增条数
     **/
    int insert(${mapperGenerateInfo.moduleName}Po insert);

    /**
     * 根据主键动态修改
     *
     * @param update
     * @return int 修改条数
     **/
    int update(${mapperGenerateInfo.moduleName}Po update);

    /**
     * 概要
     *
     * @param id
     * @return ${mapperGenerateInfo.moduleName}
     **/
    ${mapperGenerateInfo.moduleName}Detail brief(${mapperGenerateInfo.primaryKeyJavaTypeName} id);

    /**
     * 详情
     *
     * @param id
     * @return ${mapperGenerateInfo.moduleName}
     **/
    ${mapperGenerateInfo.moduleName}Detail detail(${mapperGenerateInfo.primaryKeyJavaTypeName} id);

    /**
     * 查询列表
     *
     * @return ${mapperGenerateInfo.moduleName}
     **/
    List<${mapperGenerateInfo.moduleName}${"Brief>"} list(${mapperGenerateInfo.moduleName}Query query);
}