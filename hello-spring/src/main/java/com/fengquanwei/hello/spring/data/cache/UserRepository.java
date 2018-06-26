package com.fengquanwei.hello.spring.data.cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

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

    @CachePut(value = "user", key = "#result.id")
    public User insert(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
        return user;
    }

    @Cacheable(value = "user", key = "#id", condition = "#id > 66", unless = "#result != null and #result.firstName != null and #result.firstName.equals('nocache')")
    public User select(Integer id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @CacheEvict(value = "user", key = "#user.id")
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
