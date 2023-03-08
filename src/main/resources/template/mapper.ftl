package ${generateInfo.mapperPackage};

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Po;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Query;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Brief;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Detail;
import java.util.List;

/**
 * ${generateInfo.tableComment}
 *
 * 表名：  ${generateInfo.tableName}
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 */
public interface ${generateInfo.moduleName}Mapper extends BaseMapper<${generateInfo.moduleName}Po> {

    /**
     * 新增
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
     * @param ${generateInfo.primaryKeyLowerCamel}
     * @return ${generateInfo.moduleName}Brief
     **/
    ${generateInfo.moduleName}Brief brief(${generateInfo.primaryKeyJavaTypeName} ${generateInfo.primaryKeyLowerCamel});

    /**
     * 详情
     *
     * @param ${generateInfo.primaryKeyLowerCamel}
     * @return ${generateInfo.moduleName}Detail
     **/
    ${generateInfo.moduleName}Detail detail(${generateInfo.primaryKeyJavaTypeName} ${generateInfo.primaryKeyLowerCamel});

    /**
     * 查询列表
     * @param query
     * @return List<${generateInfo.moduleName}${"Brief>"}
     **/
    List<${generateInfo.moduleName}${"Brief>"} list(${generateInfo.moduleName}Query query);
}
