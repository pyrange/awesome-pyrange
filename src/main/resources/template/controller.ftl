package ${generateInfo.controllerPackage};
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Query;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Insert;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Update;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Brief;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Detail;
import ${generateInfo.servicePackage}.${generateInfo.moduleName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${basicConfig.groupId}.common.model.dto.Result;
import java.util.List;

/**
 * ${generateInfo.tableComment}
 *
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 **/
@RestController
@RequestMapping("${generateInfo.moduleNameWithSlash}")
public class ${generateInfo.moduleName}Controller {

    @Autowired
    private ${generateInfo.moduleName}Service ${generateInfo.moduleNameLowercase}Service;

    /**
     * 新增
     *
     * @param insert
     * @return Result
     **/
    @PostMapping
    public Result insert(@RequestBody ${generateInfo.moduleName}Insert insert) {
        return ${generateInfo.moduleNameLowercase}Service.insert(insert);
    }

    /**
     * 更新
     *
     * @param update
     * @return Result
     **/
    @PutMapping
    public Result update(@RequestBody ${generateInfo.moduleName}Update update) {
        return ${generateInfo.moduleNameLowercase}Service.update(update);
    }

    /**
     * 详情
     *
     * @param id
     * @return Result${"<"}${generateInfo.moduleName}Detail${">"}
     **/
    @GetMapping("{id}")
    public Result${"<"}${generateInfo.moduleName}Detail${">"} detail(@PathVariable("id") ${generateInfo.primaryKeyJavaTypeName} id) {
        return ${generateInfo.moduleNameLowercase}Service.detail(id);
    }

    /**
     * 列表
     *
     * @param query
     * @return Result${"<List<"}${generateInfo.moduleName}Simple${">>"}
     **/
    @GetMapping
    public Result${"<List<"}${generateInfo.moduleName}Brief${">>"} list(${generateInfo.moduleName}Query query) {
        return ${generateInfo.moduleNameLowercase}Service.list(query);
    }
}