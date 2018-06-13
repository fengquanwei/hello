package com.fengquanwei.hello.hibernate.xmlconfig;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Hibernate 用例
 *
 * @author fengquanwei
 * @create 2018/6/13 14:44
 **/
public class Client {
    private static SessionFactory sessionFactory;

    @Before
    public void before() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable throwable) {
            System.err.println("Failed to create sessionFactory object" + throwable);
            throw new ExceptionInInitializerError(throwable);
        }
    }

    @Test
    public void testSave() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer employeeId = null;
        try {
            transaction = session.beginTransaction();

            Employee employee = new Employee("Zara", "Ali", 1000);
            employeeId = (Integer) session.save(employee);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        System.out.println("save: " + employeeId);
    }

    @Test
    public void testDelete() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer employeeId = 1;
        try {
            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, employeeId);
            session.delete(employee);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        System.out.println("delete: " + employeeId);
    }

    @Test
    public void testUpdate() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer employeeId = 2;
        try {
            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, employeeId);
            employee.setSalary(666);
            session.update(employee);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        System.out.println("update: " + employeeId);
    }

    @Test
    public void testQuery() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
                Employee employee = (Employee) iterator.next();
                System.out.print("First Name: " + employee.getFirstName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());
            }

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
