package com.fengquanwei.hello.spring.security.method.evaluator;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * MyPermissionEvaluator
 *
 * @author fengquanwei
 * @create 2018/7/13 16:16
 **/
public class MyPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        System.out.println("MyPermissionEvaluator.hasPermission(" + authentication + ", " + target + ", " + permission + ")");
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
