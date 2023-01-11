<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${generateInfo.basePackage}.${generateInfo.moduleName}Mapper">

    <insert id="insert" parameterType="${generateInfo.modelPath}.${generateInfo.modelNameLowerCamel}.${generateInfo.moduleName}Po" keyProperty="${generateInfo.primaryKeyCamel}" useGeneratedKeys="true">
        INSERT INTO ${generateInfo.tableName}
            (<#list generateInfo.columnList as column><#if generateInfo.primaryKey != column.columnName><#if column_has_next>${column.columnName}, <#else>${column.columnName}</#if></#if><#if (column_index+1)%8 == 0>${"\n             "}</#if></#list>)
        VALUES (
        <#list generateInfo.columnList as column>
            <#if generateInfo.primaryKey != column.columnName>
                <#if column_has_next>
            ${"#\{"}${column.columnCamelName}, jdbcType=${column.columnJdbcType}${"}"},
                <#else>
            ${"#\{"}${column.columnCamelName}, jdbcType=${column.columnJdbcType}${"}"}
                </#if>
            </#if>
        </#list>
        )
    </insert>

    <update id="update" parameterType="${generateInfo.modelPath}.${generateInfo.modelNameLowerCamel}.${generateInfo.moduleName}Po">
        UPDATE ${generateInfo.tableName}
        <set>
        <#list generateInfo.columnList as column>
            <#if generateInfo.primaryKey != column.columnName>
            <if test="${column.columnCamelName} != null">
                ${column.columnName} = ${"#\{"}${column.columnCamelName}, jdbcType=${column.columnJdbcType}${"}"},
            </if>
            </#if>
        </#list>
        </set>
        WHERE ${generateInfo.primaryKey} = ${"#\{"}${generateInfo.primaryKeyCamel}, jdbcType=${generateInfo.primaryKeyJdbcType}${"}"}
    </update>

    <select id="brief" parameterType="${generateInfo.primaryKeyJavaTypeName}" resultType="${generateInfo.modelPath}.${generateInfo.modelNameLowerCamel}.${generateInfo.moduleName}Detail">
        SELECT <#list generateInfo.columnList as column><#if column_has_next>${column.columnName},${"\n               "}<#else>${column.columnName}</#if></#list>
          FROM ${generateInfo.tableName}
         WHERE ${generateInfo.primaryKey} = ${"#\{"}${generateInfo.primaryKeyCamel}, jdbcType=${generateInfo.primaryKeyJdbcType}${"}"}
    </select>

    <select id="detail" parameterType="${generateInfo.primaryKeyJavaTypeName}" resultType="${generateInfo.modelPath}.${generateInfo.modelNameLowerCamel}.${generateInfo.moduleName}Detail">
        SELECT <#list generateInfo.columnList as column><#if column_has_next>${column.columnName},${"\n               "}<#else>${column.columnName}</#if></#list>
          FROM ${generateInfo.tableName}
         WHERE ${generateInfo.primaryKey} = ${"#\{"}${generateInfo.primaryKeyCamel}, jdbcType=${generateInfo.primaryKeyJdbcType}${"}"}
    </select>

    <select id="list" resultType="${generateInfo.modelPath}.${generateInfo.modelNameLowerCamel}.${generateInfo.moduleName}Brief">
        SELECT <#list generateInfo.columnList as column><#if column_has_next>${column.columnName},${"\n               "}<#else>${column.columnName}</#if></#list>
          FROM ${generateInfo.tableName}
         WHERE deleted = 0
    </select>
</mapper>