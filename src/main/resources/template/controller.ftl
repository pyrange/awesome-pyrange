package ${generateInfo.controllerPackage};
import ${basicConfig.groupId}.common.model.dto.Page;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Query;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Insert;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Update;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Brief;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Detail;
import ${generateInfo.servicePackage}.${generateInfo.moduleName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${basicConfig.groupId}.common.model.dto.Result;

<#if basicConfig.jdkVersion == 17>
import jakarta.validation.Valid;
<#else>
import javax.validation.Valid;
</#if>
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
    private ${generateInfo.moduleName}Service ${generateInfo.moduleNameLowercaseCamel}Service;

    /**
     * 新增
     *
     * @param insert
     * @return Result
     **/
    @PostMapping
    public Result insert(@RequestBody @Valid ${generateInfo.moduleName}Insert insert) {
        return ${generateInfo.moduleNameLowercaseCamel}Service.insert(insert);
    }

    /**
     * 删除
     *
     * @param ${generateInfo.primaryKeyLowerCamel}
     * @return Result
     **/
    @DeleteMapping("{${generateInfo.primaryKeyLowerCamel}}")
    public Result delete(@PathVariable("${generateInfo.primaryKeyLowerCamel}") ${generateInfo.primaryKeyJavaTypeName} ${generateInfo.primaryKeyLowerCamel}) {
        return ${generateInfo.moduleNameLowercaseCamel}Service.delete(${generateInfo.primaryKeyLowerCamel});
    }

    /**
     * 更新
     *
     * @param update
     * @return Result
     **/
    @PutMapping
    public Result update(@RequestBody @Valid ${generateInfo.moduleName}Update update) {
        return ${generateInfo.moduleNameLowercaseCamel}Service.update(update);
    }

    /**
     * 详情
     *
     * @param ${generateInfo.primaryKeyLowerCamel}
     * @return Result${"<"}${generateInfo.moduleName}Detail${">"}
     **/
    @GetMapping("{${generateInfo.primaryKeyLowerCamel}}")
    public Result${"<"}${generateInfo.moduleName}Detail${">"} detail(@PathVariable("${generateInfo.primaryKeyLowerCamel}") ${generateInfo.primaryKeyJavaTypeName} ${generateInfo.primaryKeyLowerCamel}) {
        return ${generateInfo.moduleNameLowercaseCamel}Service.detail(${generateInfo.primaryKeyLowerCamel});
    }

    /**
     * 列表
     *
     * @param query
     * @return Result${"<Page<List<"}${generateInfo.moduleName}Brief${">>>"}
     **/
    @GetMapping
    public Result${"<Page<List<"}${generateInfo.moduleName}Brief${">>>"} list(@ModelAttribute @Valid ${generateInfo.moduleName}Query query) {
        return ${generateInfo.moduleNameLowercaseCamel}Service.list(query);
    }
}