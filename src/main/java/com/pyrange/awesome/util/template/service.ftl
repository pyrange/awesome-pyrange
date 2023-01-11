package ${serviceGenerateInfo.basePackage};
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.moduleNameLower}.${serviceGenerateInfo.moduleName}Query;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.moduleNameLower}.${serviceGenerateInfo.moduleName}Insert;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.moduleNameLower}.${serviceGenerateInfo.moduleName}Update;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.moduleNameLower}.${serviceGenerateInfo.moduleName}Brief;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.moduleNameLower}.${serviceGenerateInfo.moduleName}Detail;
import com.pyrange.common.model.dto.Result;
import java.util.List;

/**
 * ${serviceGenerateInfo.tableComment}服务
 *
 * @author ${serviceGenerateInfo.author}
 * @date ${serviceGenerateInfo.date}
 **/
public interface ${serviceGenerateInfo.moduleName}Service {

    /**
     * 新增
     *
     * @param insert
     * @return Result
     **/
    Result insert(${serviceGenerateInfo.moduleName}Insert insert);

    /**
     * 修改
     *
     * @param update
     * @return Result
     **/
    Result update(${serviceGenerateInfo.moduleName}Update update);

    /**
     * 详情
     *
     * @param id
     * @return ${serviceGenerateInfo.moduleName}Detail
     **/
    Result${"<"}${serviceGenerateInfo.moduleName}Detail${">"} detail(Integer id);

    /**
     * 列表
     *
     * @param query
     * @return ${serviceGenerateInfo.moduleName}Brief
     **/
    Result${"<List<"}${serviceGenerateInfo.moduleName}Brief${">>"} list(${serviceGenerateInfo.moduleName}Query query);
}