package com.fengquanwei.hello.spring.mvc.model;

/**
 * User
 *
 * @author fengquanwei
 * @create 2018/6/4 16:59
 **/
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
