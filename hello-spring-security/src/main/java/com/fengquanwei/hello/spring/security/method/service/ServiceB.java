package com.fengquanwei.hello.spring.security.method.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ServiceB
 *
 * @author fengquanwei
 * @create 2018/7/13 16:02
 **/
@Service
public class ServiceB {
    @PreAuthorize("hasRole('ROLE_User') and #data.length() < 10")
    public void method4PreAuthorize(String data) {
        System.out.println("ServiceB.method4PreAuthorize(" + data + ")");
    }

    @PostAuthorize("returnObject != null and returnObject.length() > 0")
    public String method4PostAuthorize() {
        System.out.println("ServiceB.method4PostAuthorize()");
        return "Hi";
    }

    @PreAuthorize("hasRole('ROLE_User')")
    @PreFilter("filterObject != null and filterObject.length() > 0")
    public void method4PreFilter(List<String> dataList) {
        System.out.println("ServiceB.method4PreFilter(" + dataList + ")");
    }

    @PreAuthorize("hasRole('ROLE_User')")
    @PostFilter("filterObject != null and filterObject.length() > 0")
    public List<String> method4PostFilter() {
        System.out.println("ServiceB.method4PostFilter()");

        List<String> dataList = new ArrayList<>();
        dataList.add("Hi");
        dataList.add("");
        return dataList;
    }

    @PreAuthorize("hasRole('ROLE_User')")
    @PreFilter("hasPermission(filterObject, 'myPermission')")
    public void method4PermissionEvaluator(List<String> dataList) {
        System.out.println("ServiceB.method4PermissionEvaluator(" + dataList + ")");
    }
}
