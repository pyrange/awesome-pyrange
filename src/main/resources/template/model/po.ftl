package ${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot};

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
<#list generateInfo.importList as import>
import ${import};
</#list>

/**
 * ${generateInfo.tableComment}实体
 *
 * @author ${generateInfo.author}
 * @date ${generateInfo.date}
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("${generateInfo.tableName}")
public class ${generateInfo.moduleName}Po {

    <#list generateInfo.columnList as column>
    /**
      * ${column.columnComment}
      */
<#if generateInfo.primaryKey == column.columnName>
    @TableId(value = "${column.columnCamelName}", type = IdType.AUTO)
</#if>
    private ${column.columnJavaTypeName} ${column.columnCamelName};

    </#list>
}
