package ${generateInfo.basePackage}.${generateInfo.modelNameLowercase};

import lombok.*;
<#list generateInfo.importList as import>
import ${import};
</#list>

import java.time.LocalDateTime;

/**
 * ${generateInfo.tableComment}实体
 *
 * 表：${generateInfo.tableName}
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ${generateInfo.moduleName}Po {

    <#list generateInfo.columnList as column>
    /** ${column.columnComment} */
    private ${column.columnJavaTypeName} ${column.columnCamelName};

    </#list>
}