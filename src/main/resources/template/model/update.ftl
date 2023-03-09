package ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot};

import lombok.Getter;
import lombok.Setter;
<#list generateInfo.importList as import>
import ${import};
</#list>

import org.hibernate.validator.constraints.Length;
<#if basicConfig.jdkVersion == 17>
import jakarta.validation.constraints.NotNull;
<#else>
import javax.validation.constraints.NotNull;
</#if>


/**
 * ${generateInfo.tableComment}修改
 *
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 **/
@Getter
@Setter
public class ${generateInfo.moduleName}Update {

<#list generateInfo.columnList as column>
    <#if "${column.columnCamelName}"?matches("deleted|isDel|isDelete|isDeleted|createUserName|createUserId|createTime|updateUserName|updateUserId|updateTime")>
    <#else>
    /** ${column.columnComment} */
        <#if column.nullable == 'NO'>
    @NotNull(message = "${column.columnComment}不能为空")
        </#if>
        <#if column.columnJavaTypeName == 'String'>
    @Length(max = ${column.characterMaximumLength}, message = "${column.columnComment}长度不能超过${column.characterMaximumLength}位")
        </#if>
    private ${column.columnJavaTypeName} ${column.columnCamelName};

    </#if>
</#list>
}
