package ${serviceGenerateInfo.basePackage}.impl;

import ${serviceGenerateInfo.servicePackage}.${serviceGenerateInfo.moduleName}Service;
import ${serviceGenerateInfo.mapperPackage}.${serviceGenerateInfo.moduleName}Mapper;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.moduleNameLower}.${serviceGenerateInfo.moduleName}Po;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.moduleNameLower}.${serviceGenerateInfo.moduleName}Query;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.moduleNameLower}.${serviceGenerateInfo.moduleName}Insert;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.moduleNameLower}.${serviceGenerateInfo.moduleName}Update;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.moduleNameLower}.${serviceGenerateInfo.moduleName}Brief;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.moduleNameLower}.${serviceGenerateInfo.moduleName}Detail;
import com.pyrange.session.app.SessionUtil;
import com.pyrange.common.model.dto.Result;
import com.pyrange.common.util.PageUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ${serviceGenerateInfo.tableComment}服务
 *
 * @author ${serviceGenerateInfo.author}
 * @date ${serviceGenerateInfo.date}
 **/
@Service
public class ${serviceGenerateInfo.moduleName}ServiceImpl implements ${serviceGenerateInfo.moduleName}Service {

    @Autowired
    private ${serviceGenerateInfo.moduleName}Mapper ${serviceGenerateInfo.moduleNameLower}Mapper;

    @Override
    public Result insert(${serviceGenerateInfo.moduleName}Insert insert) {
        ${serviceGenerateInfo.moduleName}Po ${serviceGenerateInfo.moduleNameLower}Po = ${serviceGenerateInfo.moduleName}Po.builder()
                .createTime(LocalDateTime.now())
                .createUserId(SessionUtil.getUserId())
                .createUserName(SessionUtil.getUserName())
                .build();
        BeanUtils.copyProperties(insert, ${serviceGenerateInfo.moduleNameLower}Po);
        ${serviceGenerateInfo.moduleNameLower}Mapper.insert(${serviceGenerateInfo.moduleNameLower}Po);
        return Result.success("新增成功");
    }

    @Override
    public Result update(${serviceGenerateInfo.moduleName}Update update) {
        ${serviceGenerateInfo.moduleName}Po ${serviceGenerateInfo.moduleNameLower}Po = ${serviceGenerateInfo.moduleName}Po.builder()
                .updateTime(LocalDateTime.now())
                .updateUserId(SessionUtil.getUserId())
                .updateUserName(SessionUtil.getUserName())
                .build();
        BeanUtils.copyProperties(update, ${serviceGenerateInfo.moduleNameLower}Po);
        ${serviceGenerateInfo.moduleNameLower}Mapper.update(${serviceGenerateInfo.moduleNameLower}Po);
        return Result.success("修改成功");
    }

    @Override
    public Result${"<"}${serviceGenerateInfo.moduleName}Detail${">"} detail(Integer id) {
        return Result.success(${serviceGenerateInfo.moduleNameLower}Mapper.detail(id));
    }

    @Override
    public Result${"<List<"}${serviceGenerateInfo.moduleName}Brief${">>"} list(${serviceGenerateInfo.moduleName}Query query) {
        PageUtil.startPage(query);
        return Result.success(${serviceGenerateInfo.moduleNameLower}Mapper.list(query));
    }
}