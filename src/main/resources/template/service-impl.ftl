package ${generateInfo.servicePackage}.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    private ${generateInfo.moduleName}Mapper ${generateInfo.moduleNameLowercaseCamel}Mapper;

    @Override
    public Result insert(${generateInfo.moduleName}Insert insert) {
        ${generateInfo.moduleName}Po ${generateInfo.moduleNameLowercaseCamel}Po = ${generateInfo.moduleName}Po.builder()
                .createTime(LocalDateTime.now())
                //.createUserId(SessionUtil.getUserId())
                //.createUserName(SessionUtil.getUserName())
                .build();
        BeanUtils.copyProperties(insert, ${generateInfo.moduleNameLowercaseCamel}Po);
        ${generateInfo.moduleNameLowercaseCamel}Mapper.insert(${generateInfo.moduleNameLowercaseCamel}Po);
        return Result.success("新增成功");
    }

    @Override
    public Result delete(Integer ${generateInfo.primaryKeyLowerCamel}) {
        LambdaQueryWrapper<${generateInfo.moduleName}Po> queryWrapper = new QueryWrapper<${generateInfo.moduleName}Po>().lambda();
        queryWrapper.eq(${generateInfo.moduleName}Po::get${generateInfo.primaryKeyUpperCamel}, ${generateInfo.primaryKeyLowerCamel});
        //queryWrapper.eq(${generateInfo.moduleName}Po::getMctNo, SessionUtil.getDefaultMctNo());
        ${generateInfo.moduleNameLowercaseCamel}Mapper.delete(queryWrapper);
        return Result.success("删除成功");
    }

    @Override
    public Result update(${generateInfo.moduleName}Update update) {
        ${generateInfo.moduleName}Po ${generateInfo.moduleNameLowercaseCamel}Po = ${generateInfo.moduleName}Po.builder()
                .updateTime(LocalDateTime.now())
                .build();
        BeanUtils.copyProperties(update, ${generateInfo.moduleNameLowercaseCamel}Po);


        LambdaQueryWrapper<${generateInfo.moduleName}Po> queryWrapper = new QueryWrapper<${generateInfo.moduleName}Po>().lambda();
        queryWrapper.eq(${generateInfo.moduleName}Po::get${generateInfo.primaryKeyUpperCamel}, update.get${generateInfo.primaryKeyUpperCamel}());
        //queryWrapper.eq(${generateInfo.moduleName}Po::getMctNo, SessionUtil.getDefaultMctNo());
        ${generateInfo.moduleNameLowercaseCamel}Mapper.update(${generateInfo.moduleNameLowercaseCamel}Po, queryWrapper);
        return Result.success("修改成功");
    }

    @Override
    public Result${"<"}${generateInfo.moduleName}Detail${">"} detail(${generateInfo.primaryKeyJavaTypeName} ${generateInfo.primaryKeyLowerCamel}) {
        return Result.success(${generateInfo.moduleNameLowercaseCamel}Mapper.detail(${generateInfo.primaryKeyLowerCamel}));
    }

    @Override
    public Result${"<Page<List<"}${generateInfo.moduleName}Brief${">>>"} list(${generateInfo.moduleName}Query query) {
        if (query.getEndDate() != null) {
            query.setEndDate(query.getEndDate().plusDays(1));
        }
        PageUtil.startPage(query);
        return Result.page(${generateInfo.moduleNameLowercaseCamel}Mapper.list(query));
    }
}