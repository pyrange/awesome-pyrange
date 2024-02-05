package com.inbyte.cg.model;

/**
 * 
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class TableColumn {
    /**
     * 
     */
    private String columnName;

    /**
     * 
     */
    private String columnComment;

    /**
     * 
     */
    private String dataType;

    /**
     * 
     */
    private boolean isPrimaryKey;

    /**
     * 
     */
    private String nullable;
    /**
     * 
     */
    private String characterMaximumLength;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public String getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(String characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }
}
