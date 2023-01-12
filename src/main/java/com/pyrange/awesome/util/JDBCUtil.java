package com.pyrange.awesome.util;

import com.intellij.openapi.diagnostic.Logger;

import java.sql.*;

public class JDBCUtil {

    private String host;
    private String user;
    private String pwd;
    private String database;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private static final Logger LOGGER = Logger.getInstance(JDBCUtil.class);

    public JDBCUtil(String host, String user, String pwd) {
        this.host = host;
        this.user = user;
        this.pwd = pwd;
    }

    public JDBCUtil(String host, String database, String user, String pwd) {
        this.host = host;
        this.user = user;
        this.pwd = pwd;
        this.database = database;
    }

    public ResultSet getResultSet(String sql) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(getUrl(), user, pwd);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(getUrl(), user, pwd);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        }
        return resultSet;
    }

    public void jdbcClose() throws Exception {
        resultSet.close();
        statement.close();
        connection.close();
    }

    private String getUrl() {
        String url = "jdbc:mysql://" + host;
        if (CommonUtil.isNullOrEmpty(database)) {
            return url;
        }
        return url + "/" + database;
    }
}
