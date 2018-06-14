package com.fengquanwei.hello.javaee.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA
 *
 * @author fengquanwei
 * @create 2018/6/14 11:10
 **/
public class Client {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void before() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testPersist() {
        Student student = new Student();
        student.setId(1);
        student.setName("Lask");
        student.setAge(26);

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        System.out.println("persist: " + student);
        entityManager.getTransaction().commit();
    }

    @Test
    public void testRemove() {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, 1);
        entityManager.remove(student);
        System.out.println("remove: " + student);
        entityManager.getTransaction().commit();
    }

    @Test
    public void testUpdate() {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, 1);
        student.setName("Lynn");
        System.out.println("update: " + student);
        entityManager.getTransaction().commit();
    }

    @Test
    public void testFind() {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, 1);
        System.out.println("find: " + student);
        entityManager.getTransaction().commit();
    }

    @After
    public void after() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
