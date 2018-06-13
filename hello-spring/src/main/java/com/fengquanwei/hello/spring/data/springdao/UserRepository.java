package com.fengquanwei.hello.spring.data.springdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * UserRepository
 *
 * @author fengquanwei
 * @create 2018/6/12 17:56
 **/
@Repository
public class UserRepository {
    @Autowired
    JdbcOperations jdbcOperations;

    @Autowired
    NamedParameterJdbcOperations namedParameterJdbcOperations;

    public int insertUser(User user) {
        return jdbcOperations.update("insert into user values (?, ?, ?, ?)",
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getSalary());
    }

    public int insertUser(Map<String, Object> paramMap) {
        return namedParameterJdbcOperations.update("insert into user values (:id, :firstName, :lastName, :salary)", paramMap);
    }

    public int updateUser(User user) {
        return jdbcOperations.update("update user set id = ?, first_name = ?, last_name = ?, salary = ? where id = ?",
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getSalary(),
                user.getId());
    }

    public User selectOneUser(Integer id) {
        return jdbcOperations.queryForObject("select * from user where id = ? limit 1", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("salary"));
            }
        }, id);
    }
}
