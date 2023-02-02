<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${generateInfo.mapperPackage}.${generateInfo.moduleName}Mapper">

    <insert id="insert" parameterType="${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Po" keyProperty="${generateInfo.primaryKeyLowerCamel}" useGeneratedKeys="true">
        INSERT INTO ${generateInfo.tableName}
            (<#list generateInfo.columnList as column><#if column_has_next><#if generateInfo.primaryKey != column.columnName && !column.columnName?matches("deleted|isDel|isDelete|isDeleted|updateUserName|updateUserId|updateTime")>${column.columnName}, </#if><#else>${column.columnName}</#if><#if (column_index+1)%8 == 0>${"\n             "}</#if></#list>)
        VALUES (
        <#list generateInfo.columnList as column>
            <#if generateInfo.primaryKey != column.columnName>
                <#if column_has_next>
                    <#if generateInfo.primaryKey != column.columnName && !column.columnName?matches("deleted|isDel|isDelete|isDeleted|updateUserName|updateUserId|updateTime")>
            ${"#\{"}${column.columnCamelName}, jdbcType=${column.columnJdbcType}${"}"},
                    </#if>
                <#else>
            ${"#\{"}${column.columnCamelName}, jdbcType=${column.columnJdbcType}${"}"}
                </#if>
            </#if>
        </#list>
        )
    </insert>

    <update id="update" parameterType="${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Po">
        UPDATE ${generateInfo.tableName}
        <set>
        <#list generateInfo.columnList as column>
            <#if generateInfo.primaryKey != column.columnName && !column.columnName?matches("createUserName|createUserId|createTime")>
            <if test="${column.columnCamelName} != null">
                ${column.columnName} = ${"#\{"}${column.columnCamelName}, jdbcType=${column.columnJdbcType}${"}"},
            </if>
            </#if>
        </#list>
        </set>
        WHERE ${generateInfo.primaryKey} = ${"#\{"}${generateInfo.primaryKeyLowerCamel}, jdbcType=${generateInfo.primaryKeyJdbcType}${"}"}
    </update>

    <select id="brief" parameterType="${generateInfo.primaryKeyJavaTypeName}" resultType="${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Brief">
        SELECT <#list generateInfo.columnList as column><#if column_has_next><#if !"${column.columnCamelName}"?matches("deleted|isDel|isDelete|isDeleted|createUserName|createUserId|createTime|updateUserName|updateUserId|updateTime")>${column.columnName},${"\n               "}</#if><#else>${column.columnName}</#if></#list>
          FROM ${generateInfo.tableName}
         WHERE ${generateInfo.primaryKey} = ${"#\{"}${generateInfo.primaryKeyLowerCamel}, jdbcType=${generateInfo.primaryKeyJdbcType}${"}"}
    </select>

    <select id="detail" parameterType="${generateInfo.primaryKeyJavaTypeName}" resultType="${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Detail">
        SELECT <#list generateInfo.columnList as column><#if column_has_next><#if !"${column.columnCamelName}"?matches("deleted|isDel|isDelete|isDeleted")>${column.columnName},${"\n               "}</#if><#else>${column.columnName}</#if></#list>
          FROM ${generateInfo.tableName}
         WHERE ${generateInfo.primaryKey} = ${"#\{"}${generateInfo.primaryKeyLowerCamel}, jdbcType=${generateInfo.primaryKeyJdbcType}${"}"}
    </select>

    <select id="list" resultType="${generateInfo.modelPackage}.${generateInfo.moduleNameWithDot}.${generateInfo.moduleName}Brief">
        SELECT <#list generateInfo.columnList as column><#if column_has_next><#if !"${column.columnCamelName}"?matches("deleted|isDel|isDelete|isDeleted|createUserName|createUserId|updateUserName|updateUserId|updateTime")>${column.columnName},${"\n               "}</#if><#else>${column.columnName}</#if></#list>
          FROM ${generateInfo.tableName}
         WHERE deleted = 0
        <if test="startDate != null">
           AND createTime &gt; <#noparse>#{startDate, jdbcType=TIMESTAMP}</#noparse>
        </if>
        <if test="endDate != null">
           AND createTime &lt; <#noparse>#{endDate, jdbcType=TIMESTAMP}</#noparse>
        </if>
         ORDER BY ${generateInfo.primaryKey} DESC
    </select>
</mapper>