package ${generateInfo.servicePackage};
import ${basicConfig.groupId}.common.model.dto.Page;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Query;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Insert;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Update;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Brief;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Detail;
import ${basicConfig.groupId}.common.model.dto.Result;
import java.util.List;

/**
 * ${generateInfo.tableComment}服务
 *
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 **/
public interface ${generateInfo.moduleName}Service {

    /**
     * 新增
     *
     * @param insert
     * @return Result
     **/
    Result insert(${generateInfo.moduleName}Insert insert);

    /**
     * 删除
     *
     * @param ${generateInfo.primaryKeyLowerCamel}
     * @return Result
     **/
    Result delete(${generateInfo.primaryKeyJavaTypeName} ${generateInfo.primaryKeyLowerCamel});

    /**
     * 修改
     *
     * @param update
     * @return Result
     **/
    Result update(${generateInfo.moduleName}Update update);

    /**
     * 详情
     *
     * @param ${generateInfo.primaryKeyLowerCamel}
     * @return ${generateInfo.moduleName}Detail
     **/
    Result${"<"}${generateInfo.moduleName}Detail${">"} detail(${generateInfo.primaryKeyJavaTypeName} ${generateInfo.primaryKeyLowerCamel});

    /**
     * 列表
     *
     * @param query
     * @return Result${"<Page<List<"}${generateInfo.moduleName}Brief${">>>"}
     **/
    Result${"<Page<List<"}${generateInfo.moduleName}Brief${">>>"} list(${generateInfo.moduleName}Query query);
}