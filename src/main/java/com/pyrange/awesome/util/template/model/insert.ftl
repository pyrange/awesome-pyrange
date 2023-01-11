package ${modelGenerateInfo.basePackage}.${modelGenerateInfo.modelNameLowercase};

import lombok.Getter;
import lombok.Setter;
<#list modelGenerateInfo.importList as import>
import ${import};
</#list>
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

/**
 * ${modelGenerateInfo.tableComment}创建
 *
 * @author ${modelGenerateInfo.author}
 * @date ${modelGenerateInfo.date}
 **/
@Getter
@Setter
public class ${modelGenerateInfo.moduleName}Insert {

    // TODO 记得删除无用字段；记得删除无用字段；记得删除无用字段；记得删除无用字段；记得删除无用字段；
<#list modelGenerateInfo.columnList as column>

    /** ${column.columnComment} */
    <#if column.nullable == 'NO'>
    @NotNull(message = "${column.columnComment}不能为空")
    </#if>
    <#if column.columnJavaTypeName == 'String'>
    @Length(min = 1, max = ${column.characterMaximumLength}, message = "${column.columnComment}长度不能超过${column.characterMaximumLength}位")
    </#if>
    private ${column.columnJavaTypeName} ${column.columnCamelName};
</#list>
}