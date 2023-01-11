package ${modelGenerateInfo.basePackage}.${modelGenerateInfo.modelNameLowercase};

import lombok.*;
<#list modelGenerateInfo.importList as import>
import ${import};
</#list>

import java.time.LocalDateTime;

/**
 * ${modelGenerateInfo.tableComment}实体
 *
 * 表：${modelGenerateInfo.tableName}
 * @author ${modelGenerateInfo.author}
 * @date ${modelGenerateInfo.date}
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ${modelGenerateInfo.moduleName}Po {

    <#list modelGenerateInfo.columnList as column>
    /** ${column.columnComment} */
    private ${column.columnJavaTypeName} ${column.columnCamelName};

    </#list>
}