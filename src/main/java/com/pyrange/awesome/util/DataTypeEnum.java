package com.pyrange.awesome.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public enum DataTypeEnum {
    ARRAY("array", "ARRAY", Object.class.getName(), "Object"),
    BIGINT("bigint", "BIGINT", Long.class.getName(), "Long"),
    BINARY("binary", "BINARY", "byte[]", "byte[]"),
    BIT("bit", "BIT", Boolean.class.getName(), "Boolean"),
    BLOB("blob", "BLOB", "byte[]", "byte[]"),
    BOOLEAN("boolean", "BOOLEAN", Boolean.class.getName(), "Boolean"),
    CHAR("char", "CHAR", String.class.getName(), "String"),
    CLOB("clob", "CLOB", String.class.getName(), "String"),
    DATALINK("datalink", "DATALINK", Object.class.getName(), "Object"),
    DATE("date", "DATE", LocalDate.class.getName(), "LocalDate"),
    DATETIME("datetime", "TIMESTAMP", LocalDateTime.class.getName(), "LocalDateTime"),
    DECIMAL("decimal", "DECIMAL", BigDecimal.class.getName(), "BigDecimal"),
    DISTINCT("distinct", "DISTINCT", Object.class.getName(), "Object"),
    DOUBLE("double", "DOUBLE", Double.class.getName(), "Double"),
    FLOAT("float", "FLOAT", Double.class.getName(), "Double"),
    INTEGER("int", "INTEGER", Integer.class.getName(), "Integer"),
    JAVA_OBJECT("java_object", "JAVA_OBJECT", Object.class.getName(), "Object"),
    LONGNVARCHAR("longnvarchar", "LONGNVARCHAR", String.class.getName(), "String"),
    LONGVARBINARY("longvarbinary", "LONGVARBINARY", "byte[]", "byte[]"),
    LONGVARCHAR("longvarchar", "LONGVARCHAR", String.class.getName(), "String"),
    NCHAR("nchar", "NCHAR", String.class.getName(), "String"),
    NCLOB("nclob", "NCLOB", String.class.getName(), "String"),
    NVARCHAR("nvarchar", "NVARCHAR", String.class.getName(), "String"),
    NULL("null", "NULL", Object.class.getName(), "Object"),
    NUMERIC("numeric", "NUMERIC", BigDecimal.class.getName(), "BigDecimal"),
    OTHER("other", "OTHER", Object.class.getName(), "Object"),
    REAL("real", "REAL", Float.class.getName(), "Float"),
    REF("ref", "REF", Object.class.getName(), "Object"),
    SMALLINT("smallint", "SMALLINT", Integer.class.getName(), "Integer"),
    STRUCT("struct", "STRUCT", Object.class.getName(), "Object"),
    TIME("time", "TIME", LocalTime.class.getName(), "LocalTime"),
    TIMESTAMP("timestamp", "TIMESTAMP", LocalDateTime.class.getName(), "LocalDateTime"),
    TINYINT("tinyint", "TINYINT", Integer.class.getName(), "Integer"),
    VARBINARY("varbinary", "VARBINARY", "byte[]", "byte[]"),
    VARCHAR("varchar", "VARCHAR", String.class.getName(), "String"),
    TEXT("text", "LONGVARCHAR", String.class.getName(), "String");

    public final String dataType;
    public final String jdbcType;
    public final String javaType;
    public final String javaTypeName;

    DataTypeEnum(String dataType, String jdbcType, String javaType, String javaTypeName) {
        this.dataType = dataType;
        this.jdbcType = jdbcType;
        this.javaType = javaType;
        this.javaTypeName = javaTypeName;
    }

    public static String getJdbcTypeByDataType(String dataType) {
        DataTypeEnum dataTypeEnum = getEnumByDataType(dataType);
        if(dataTypeEnum == null){
            return "VARCHAR";
        }
        return dataTypeEnum.jdbcType;
    }

    public static String getJavaTypeByDataType(String dataType) {
        DataTypeEnum dataTypeEnum = getEnumByDataType(dataType);
        if(dataTypeEnum == null){
            return "java.lang.Object";
        }
        return dataTypeEnum.javaType;
    }

    public static String getJavaTypeNameByDataType(String dataType) {
        DataTypeEnum dataTypeEnum = getEnumByDataType(dataType);
        if(dataTypeEnum == null){
            return "Object";
        }
        return dataTypeEnum.javaTypeName;
    }

    public static DataTypeEnum getEnumByDataType(String dataType) {
        DataTypeEnum[] values = DataTypeEnum.values();
        for (DataTypeEnum dataTypeEnum : values) {
            if(dataTypeEnum.dataType.equals(dataType)){
                return dataTypeEnum;
            }
        }
        return null;
    }
}

