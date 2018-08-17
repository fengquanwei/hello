package com.fengquanwei.hello.javase.jdk.database.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工具：线程独享一个连接
 *
 * @author fengquanwei
 * @create 2017/12/1 17:29
 **/
public class ThreadLocalDBUtil {
    // 数据库配置
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/demo";
    private static final String username = "root";
    private static final String password = "kilogate";

    // 数据库连接
    private static ThreadLocal<Connection> connectionContainer = new ThreadLocal<>();

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection connection = connectionContainer.get();
        try {
            if (connection == null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionContainer.set(connection);
        }

        return connection;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection() {
        Connection connection = connectionContainer.get();
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionContainer.remove();
        }
    }


}
