package ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot};

import lombok.Getter;
import lombok.Setter;
<#list generateInfo.importList as import>
import ${import};
</#list>
import java.time.LocalDateTime;

/**
 * ${generateInfo.tableComment}摘要
 *
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 **/
@Getter
@Setter
public class ${generateInfo.moduleName}Brief {

    // TODO ${basicConfig.author} 记得删除无用字段；记得删除无用字段；记得删除无用字段；记得删除无用字段；记得删除无用字段；
<#list generateInfo.columnList as column>

    <#if "${column.columnCamelName}"?matches("deleted|createUserName|createUserId|createTime|updateUserName|updateUserId|updateTime")>
    <#else>
    /** ${column.columnComment} */
    private ${column.columnJavaTypeName} ${column.columnCamelName};
    </#if>
</#list>
}