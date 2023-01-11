<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperGenerateInfo.basePackage}.${mapperGenerateInfo.moduleName}Mapper">

    <insert id="insert" parameterType="${mapperGenerateInfo.modelPath}.${mapperGenerateInfo.modelNameLowerCamel}.${mapperGenerateInfo.moduleName}Po" keyProperty="${mapperGenerateInfo.primaryKeyCamel}" useGeneratedKeys="true">
        INSERT INTO ${mapperGenerateInfo.tableName}
            (<#list mapperGenerateInfo.columnList as column><#if mapperGenerateInfo.primaryKey != column.columnName><#if column_has_next>${column.columnName}, <#else>${column.columnName}</#if></#if><#if (column_index+1)%8 == 0>${"\n             "}</#if></#list>)
        VALUES (
        <#list mapperGenerateInfo.columnList as column>
            <#if mapperGenerateInfo.primaryKey != column.columnName>
                <#if column_has_next>
            ${"#\{"}${column.columnCamelName}, jdbcType=${column.columnJdbcType}${"}"},
                <#else>
            ${"#\{"}${column.columnCamelName}, jdbcType=${column.columnJdbcType}${"}"}
                </#if>
            </#if>
        </#list>
        )
    </insert>

    <update id="update" parameterType="${mapperGenerateInfo.modelPath}.${mapperGenerateInfo.modelNameLowerCamel}.${mapperGenerateInfo.moduleName}Po">
        UPDATE ${mapperGenerateInfo.tableName}
        <set>
        <#list mapperGenerateInfo.columnList as column>
            <#if mapperGenerateInfo.primaryKey != column.columnName>
            <if test="${column.columnCamelName} != null">
                ${column.columnName} = ${"#\{"}${column.columnCamelName}, jdbcType=${column.columnJdbcType}${"}"},
            </if>
            </#if>
        </#list>
        </set>
        WHERE ${mapperGenerateInfo.primaryKey} = ${"#\{"}${mapperGenerateInfo.primaryKeyCamel}, jdbcType=${mapperGenerateInfo.primaryKeyJdbcType}${"}"}
    </update>

    <select id="brief" parameterType="${mapperGenerateInfo.primaryKeyJavaTypeName}" resultType="${mapperGenerateInfo.modelPath}.${mapperGenerateInfo.modelNameLowerCamel}.${mapperGenerateInfo.moduleName}Detail">
        SELECT <#list mapperGenerateInfo.columnList as column><#if column_has_next>${column.columnName},${"\n               "}<#else>${column.columnName}</#if></#list>
          FROM ${mapperGenerateInfo.tableName}
         WHERE ${mapperGenerateInfo.primaryKey} = ${"#\{"}${mapperGenerateInfo.primaryKeyCamel}, jdbcType=${mapperGenerateInfo.primaryKeyJdbcType}${"}"}
    </select>

    <select id="detail" parameterType="${mapperGenerateInfo.primaryKeyJavaTypeName}" resultType="${mapperGenerateInfo.modelPath}.${mapperGenerateInfo.modelNameLowerCamel}.${mapperGenerateInfo.moduleName}Detail">
        SELECT <#list mapperGenerateInfo.columnList as column><#if column_has_next>${column.columnName},${"\n               "}<#else>${column.columnName}</#if></#list>
          FROM ${mapperGenerateInfo.tableName}
         WHERE ${mapperGenerateInfo.primaryKey} = ${"#\{"}${mapperGenerateInfo.primaryKeyCamel}, jdbcType=${mapperGenerateInfo.primaryKeyJdbcType}${"}"}
    </select>

    <select id="list" resultType="${mapperGenerateInfo.modelPath}.${mapperGenerateInfo.modelNameLowerCamel}.${mapperGenerateInfo.moduleName}Brief">
        SELECT <#list mapperGenerateInfo.columnList as column><#if column_has_next>${column.columnName},${"\n               "}<#else>${column.columnName}</#if></#list>
          FROM ${mapperGenerateInfo.tableName}
         WHERE deleted = 0
    </select>
</mapper>