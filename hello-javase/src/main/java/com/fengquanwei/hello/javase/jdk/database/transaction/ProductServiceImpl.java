package com.fengquanwei.hello.javase.jdk.database.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 产品服务实现类
 *
 * @author fengquanwei
 * @create 2017/12/1 17:50
 **/
public class ProductServiceImpl implements ProductService {
    private static final String UPDATE_PRODUCT_SQL = "update product set price = ? where id = ?";
    private static final String INSERT_LOG_SQL = "insert into log (created, description) values (?, ?)";

    @Override
    public void updateProductPrice(long productId, int price) {
        try {
            // 获取连接
//            Connection connection = DBUtil.getConnection();
            Connection connection = ThreadLocalDBUtil.getConnection();
            connection.setAutoCommit(false); // 关闭自动提交事务（开启事务）

            // 执行操作
            updateProduct(connection, UPDATE_PRODUCT_SQL, productId, price);
            insertLog(connection, INSERT_LOG_SQL, "Update product.");

            // 提交事务
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
//            DBUtil.closeConnection();
            ThreadLocalDBUtil.closeConnection();
        }
    }

    /**
     * 更新产品
     */
    private void updateProduct(Connection connection, String updateProductSql, long productId, int productPrice) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(updateProductSql);
        preparedStatement.setInt(1, productPrice);
        preparedStatement.setLong(2, productId);
        int rows = preparedStatement.executeUpdate();
        if (rows != 0) {
            System.out.println("Update product success!");
        }
    }

    /**
     * 插入日志
     */
    private void insertLog(Connection connection, String insertLogSql, String logDescription) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(insertLogSql);
        preparedStatement.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        preparedStatement.setString(2, logDescription);
        int rows = preparedStatement.executeUpdate();
        if (rows != 0) {
            System.out.println("Insert log success!");
        }
    }
}
