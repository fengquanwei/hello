package com.fengquanwei.hello.spring.mvc.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User
 *
 * @author fengquanwei
 * @create 2018/6/4 16:59
 **/
public class User {
    @NotNull
    @Size(min = 4, max = 10)
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

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
