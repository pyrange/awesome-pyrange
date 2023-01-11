package ${generateInfo.basePackage}.${generateInfo.modelNameLowercase};

import lombok.Getter;
import lombok.Setter;
<#list generateInfo.importList as import>
import ${import};
</#list>
import java.time.LocalDateTime;

/**
 * ${generateInfo.tableComment}详情
 *
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 **/
@Getter
@Setter
public class ${generateInfo.moduleName}Detail {

    // TODO 记得删除无用字段；记得删除无用字段；记得删除无用字段；记得删除无用字段；记得删除无用字段；
<#list generateInfo.columnList as column>

    /** ${column.columnComment} */
    private ${column.columnJavaTypeName} ${column.columnCamelName};
</#list>
}