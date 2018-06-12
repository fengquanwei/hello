package com.fengquanwei.hello.spring.data.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * JdbcUtil
 *
 * @author fengquanwei
 * @create 2018/6/12 10:57
 **/
public class JdbcUtil {
    private static String USERNAME;
    private static String PASSWORD;
    private static String DRIVER;
    private static String URL;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    static {
        loadConfig();
    }

    public static void loadConfig() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = JdbcUtil.class.getResourceAsStream("/spring-jdbc.properties");
            properties.load(inputStream);

            USERNAME = properties.getProperty("jdbc.username");
            PASSWORD = properties.getProperty("jdbc.password");
            DRIVER = properties.getProperty("jdbc.driver");
            URL = properties.getProperty("jdbc.url");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("getConnection error", e);
        }
        return connection;
    }

    public int update(String sql, List<?> params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
        }

        return preparedStatement.executeUpdate();
    }

    public List<Map<String, Object>> query(String sql, List<?> params) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();

        preparedStatement = connection.prepareStatement(sql);
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
        }

        resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnName(i + 1);
                Object columnValue = resultSet.getObject(columnName);
                if (columnValue == null) {
                    columnValue = "";
                }
                map.put(columnName, columnValue);
            }
            result.add(map);
        }

        return result;
    }

    public void closeConnection() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JdbcUtil jdbcUtil = new JdbcUtil();

        jdbcUtil.getConnection();

        try {
            int update = jdbcUtil.update("update a set score = ? where id = ?", new ArrayList() {{
                add("777");
                add("3");
            }});
            System.out.println("update: " + update);

            List<Map<String, Object>> result = jdbcUtil.query("select * from a where id > ?", new ArrayList() {{
                add("1");
            }});
            for (Map<String, Object> map : result) {
                System.out.println(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeConnection();
        }
    }
}
