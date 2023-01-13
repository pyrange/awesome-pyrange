package ${generateInfo.servicePackage};
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Query;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Insert;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Update;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Brief;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Detail;
import ${configModel.groupId}.common.model.dto.Result;
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
     * 修改
     *
     * @param update
     * @return Result
     **/
    Result update(${generateInfo.moduleName}Update update);

    /**
     * 详情
     *
     * @param id
     * @return ${generateInfo.moduleName}Detail
     **/
    Result${"<"}${generateInfo.moduleName}Detail${">"} detail(${generateInfo.primaryKeyJavaType} id);

    /**
     * 列表
     *
     * @param query
     * @return ${generateInfo.moduleName}Brief
     **/
    Result${"<List<"}${generateInfo.moduleName}Brief${">>"} list(${generateInfo.moduleName}Query query);
}