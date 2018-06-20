package com.fengquanwei.hello.spring.data.springdatamongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * User
 *
 * @author fengquanwei
 * @create 2018/6/13 11:12
 **/
@Document
public class User {
    @Id
    private int id;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    // 不加 Field 注解，默认同名
    private int salary;

    private List<Contact> contacts;

    public User(int id, String firstName, String lastName, int salary, List<Contact> contacts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", contacts=" + contacts +
                '}';
    }
}
