package ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot};

import lombok.Getter;
import lombok.Setter;
<#list generateInfo.importList as import>
import ${import};
</#list>

/**
 * ${generateInfo.tableComment}摘要
 *
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 **/
@Getter
@Setter
public class ${generateInfo.moduleName}Brief {

<#list generateInfo.columnList as column>
    <#if "${column.columnCamelName}"?matches("deleted|isDel|isDelete|isDeleted|createUserName|createUserId|updateUserName|updateUserId|updateTime")>
    <#else>
    /** ${column.columnComment} */
    private ${column.columnJavaTypeName} ${column.columnCamelName};

    </#if>
</#list>
}
