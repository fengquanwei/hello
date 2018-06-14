package com.fengquanwei.hello.spring.data.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * UserRepository
 *
 * @author fengquanwei
 * @create 2018/6/12 17:56
 **/
@Repository
@Transactional
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void insert(User user) {
        entityManager.persist(user);
    }

    public User selectOne(Integer id) {
        return entityManager.find(User.class, id);
    }

    public void update(User user) {
        entityManager.merge(user);
    }
}
