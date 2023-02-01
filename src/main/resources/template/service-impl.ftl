package ${generateInfo.servicePackage}.impl;

import ${basicConfig.groupId}.common.model.dto.Page;
import ${generateInfo.servicePackage}.${generateInfo.moduleName}Service;
import ${generateInfo.mapperPackage}.${generateInfo.moduleName}Mapper;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Po;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Query;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Insert;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Update;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Brief;
import ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Detail;
import com.pyrange.common.model.dto.Result;
import com.pyrange.common.util.PageUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ${generateInfo.tableComment}服务
 *
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 **/
@Service
public class ${generateInfo.moduleName}ServiceImpl implements ${generateInfo.moduleName}Service {

    @Autowired
    private ${generateInfo.moduleName}Mapper ${generateInfo.moduleNameLowercase}Mapper;

    @Override
    public Result insert(${generateInfo.moduleName}Insert insert) {
        ${generateInfo.moduleName}Po ${generateInfo.moduleNameLowercase}Po = ${generateInfo.moduleName}Po.builder()
                .createTime(LocalDateTime.now())
                .build();
        BeanUtils.copyProperties(insert, ${generateInfo.moduleNameLowercase}Po);
        ${generateInfo.moduleNameLowercase}Mapper.insert(${generateInfo.moduleNameLowercase}Po);
        return Result.success("新增成功");
    }

    @Override
    public Result delete(Integer ${generateInfo.primaryKeyLowerCamel}) {
        ${generateInfo.moduleName}Po ${generateInfo.moduleNameLowercase}Po = ${generateInfo.moduleName}Po.builder()
            .${generateInfo.primaryKeyLowerCamel}(${generateInfo.primaryKeyLowerCamel})
            .updateTime(LocalDateTime.now())
            .deleted(1)
            .build();
        ${generateInfo.moduleNameLowercase}Mapper.update(${generateInfo.moduleNameLowercase}Po);
        return Result.success("删除成功");
    }

    @Override
    public Result update(${generateInfo.moduleName}Update update) {
        ${generateInfo.moduleName}Po ${generateInfo.moduleNameLowercase}Po = ${generateInfo.moduleName}Po.builder()
                .updateTime(LocalDateTime.now())
                .build();
        BeanUtils.copyProperties(update, ${generateInfo.moduleNameLowercase}Po);
        ${generateInfo.moduleNameLowercase}Mapper.update(${generateInfo.moduleNameLowercase}Po);
        return Result.success("修改成功");
    }

    @Override
    public Result${"<"}${generateInfo.moduleName}Detail${">"} detail(${generateInfo.primaryKeyJavaTypeName} ${generateInfo.primaryKeyLowerCamel}) {
        return Result.success(${generateInfo.moduleNameLowercase}Mapper.detail(${generateInfo.primaryKeyLowerCamel}));
    }

    @Override
    public Result${"<Page<List<"}${generateInfo.moduleName}Brief${">>>"} list(${generateInfo.moduleName}Query query) {
        if (query.getEndDate() != null) {
            query.setEndDate(query.getEndDate().plusDays(1));
        }
        PageUtil.startPage(query);
        return Result.page(${generateInfo.moduleNameLowercase}Mapper.list(query));
    }
}