package com.fengquanwei.hello.spring.data.spring;

import com.fengquanwei.hello.spring.data.spring.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
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
        return jdbcOperations.update("insert into user values (?, ?)", user.getId(), user.getName());
    }

    public int insertUser(Map<String, Object> paramMap) {
        return namedParameterJdbcOperations.update("insert into user values (:id, :name)", paramMap);
    }

    public int updateUser(User user) {
        return jdbcOperations.update("update user set id = ?, name = ? where id = ?", user.getId(), user.getName(), user.getId());
    }

    public User selectOneUser(Integer id) {
        return jdbcOperations.queryForObject("select * from user where id = ? limit 1", (ResultSet resultSet, int i) -> {
            return new User(resultSet.getInt("id"), resultSet.getString("name"));
        }, id);
    }
}
