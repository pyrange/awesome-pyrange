package com.inbyte.cg.model;


import java.util.List;
/**
 * 
 *
 * @author : yangning
 * @date: 2018-6-11
 **/

public class TableInfo {
    /**
     * 
     */
    private String tableName;

    /**
     * 
     */
    private String tableComment;

    /**
     * 
     */
    private List<TableColumn> tableColumns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<TableColumn> getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(List<TableColumn> tableColumns) {
        this.tableColumns = tableColumns;
    }
}
