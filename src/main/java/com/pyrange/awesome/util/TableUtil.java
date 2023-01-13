package com.pyrange.awesome.util;

import com.intellij.openapi.diagnostic.Logger;
import com.pyrange.awesome.PyrangeException;
import com.pyrange.awesome.model.TableInfo;
import com.pyrange.awesome.model.TableColumn;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TableUtil {
    private String host;
    private String database;
    private String user;
    private String pwd;
    private static final Logger LOGGER = Logger.getInstance(TableUtil.class);

    public TableUtil(String host, String database, String user, String pwd) {
        this.host = host;
        this.user = user;
        this.pwd = pwd;
        this.database = database;
    }

    public TableUtil(String host, String user, String pwd) {
        this.host = host;
        this.user = user;
        this.pwd = pwd;
    }

    public TableInfo getTableInfo(String tableName) throws Exception {
        JDBCUtil jdbcUtil = new JDBCUtil(host, database, user, pwd);
        StringBuilder sql = new StringBuilder()
                .append("SHOW TABLE STATUS WHERE NAME = '").append(tableName).append("'");
        String tableComment = "";
        ResultSet resultSet = jdbcUtil.getResultSet(sql.toString());
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount++;
            tableComment = resultSet.getString("Comment");
        }
        if (rowCount == 0) {
            throw new PyrangeException("Table '" + tableName + "' not found in '" + database + "'");
        }
        jdbcUtil.jdbcClose();
        if (CommonUtil.isNullOrEmpty(tableComment)) {
            throw new PyrangeException("Please add a comment for table '" + tableName + "'");
        }
        TableInfo tableBase = new TableInfo();
        tableBase.setTableName(tableName);
        tableBase.setTableComment(tableComment);
        tableBase.setTableColumns(getTableColumns(tableName));
        return tableBase;
    }

    public List<TableColumn> getTableColumns(String tableName) throws Exception {
        JDBCUtil jdbcUtil = new JDBCUtil(host, database, user, pwd);
        StringBuilder sql = new StringBuilder()
                .append("SELECT column_name,column_comment,data_type,column_key,is_nullable,character_maximum_length FROM information_schema.columns")
                .append(" WHERE table_schema = '").append(database)
                .append("'  AND table_name = '").append(tableName).append("'")
                .append(" ORDER BY ordinal_position");
        List<TableColumn> tableColumns = new ArrayList<>();
        ResultSet resultSet = jdbcUtil.getResultSet(sql.toString());
        while (resultSet.next()) {
            TableColumn tableColumn = new TableColumn();
            tableColumn.setColumnName(resultSet.getString("column_name"));
            tableColumn.setColumnComment(resultSet.getString("column_comment"));
            tableColumn.setDataType(resultSet.getString("data_type"));
            tableColumn.setCharacterMaximumLength(resultSet.getString("character_maximum_length"));
            tableColumn.setNullable(resultSet.getString("is_nullable"));

            if ("PRI".equals(resultSet.getString("column_key"))) {
                tableColumn.setPrimaryKey(true);
            } else {
                tableColumn.setPrimaryKey(false);
            }
            tableColumns.add(tableColumn);
        }
        jdbcUtil.jdbcClose();
        return tableColumns;
    }

    public List<String> getAllDatabase() throws Exception {
        JDBCUtil jdbcUtil = new JDBCUtil(host, user, pwd);
        StringBuilder sql = new StringBuilder().append("SHOW DATABASES");
        List<String> databases = new ArrayList<>();
        ResultSet resultSet = jdbcUtil.getResultSet(sql.toString());
        while (resultSet.next()) {
            String database = resultSet.getString("Database");
            databases.add(database);
        }
        jdbcUtil.jdbcClose();
        return databases;
    }

}
