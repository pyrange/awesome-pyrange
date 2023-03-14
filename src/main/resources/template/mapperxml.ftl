<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${generateInfo.mapperPackage}.${generateInfo.moduleName}Mapper">

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
        <if test="keyword != null and keyword != ''">
            -- TODO ${generateInfo.author} don't forget to modify the column name
            AND (name LIKE CONCAT('%', <#noparse>#{keyword}</#noparse>, '%')
            OR tel LIKE CONCAT('%', <#noparse>#{keyword}</#noparse>, '%'))
        </if>
         ORDER BY ${generateInfo.primaryKey} DESC
    </select>
</mapper>