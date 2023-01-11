package ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot};

import lombok.Getter;
import lombok.Setter;
<#list generateInfo.importList as import>
import ${import};
</#list>
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

/**
 * ${generateInfo.tableComment}创建
 *
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 **/
@Getter
@Setter
public class ${generateInfo.moduleName}Insert {

    // TODO 记得删除无用字段；记得删除无用字段；记得删除无用字段；记得删除无用字段；记得删除无用字段；
<#list generateInfo.columnList as column>

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