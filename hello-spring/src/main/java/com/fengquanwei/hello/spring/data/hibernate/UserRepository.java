package com.fengquanwei.hello.spring.data.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepository
 *
 * @author fengquanwei
 * @create 2018/6/12 17:56
 **/
@Repository
public class UserRepository {
    @Autowired
    SessionFactory sessionFactory;

    public int insert(User user) {
        Session session = sessionFactory.openSession();
        int id = (int) session.save(user);
        session.close();
        return id;
    }

    public User selectOne(Integer id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public List<User> selectAll() {
        Session session = sessionFactory.openSession();
        List userList = session.createCriteria(User.class).list();
        session.close();
        return userList;
    }
}
