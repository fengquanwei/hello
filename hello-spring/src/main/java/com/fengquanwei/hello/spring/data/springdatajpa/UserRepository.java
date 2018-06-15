package com.fengquanwei.hello.spring.data.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * UserRepository
 *
 * @author fengquanwei
 * @create 2018/6/15 15:30
 **/
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByFirstName(String firstName);

    @Query("select u from User u where u.salary > 100")
    List<User> findAllFatSalary();
}
