package com.fengquanwei.hello.spring.security.method.test;

import com.fengquanwei.hello.spring.security.method.config.MyMethodSecurityConfig;
import com.fengquanwei.hello.spring.security.method.service.ServiceA;
import com.fengquanwei.hello.spring.security.method.service.ServiceB;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Client
 *
 * @author fengquanwei
 * @create 2018/7/13 19:16
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyMethodSecurityConfig.class)
public class Client {
    @Autowired
    private ServiceA serviceA;

    @Autowired
    private ServiceB serviceB;

    @Before
    public void clearContext() {
        SecurityContextHolder.clearContext();
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void test1() {
        serviceA.methodA();
    }

    @Test(expected = AccessDeniedException.class)
    public void test2() {
        setupUser();
        serviceA.methodA();
    }

    @Test
    public void test3() {
        setupUser("ROLE_User");
        serviceA.methodA();
    }

    @Test
    public void test4(){
        setupUser("ROLE_User");
        serviceB.method4PreAuthorize("Hello");
    }

    @Test
    public void test5(){
        setupUser("ROLE_User");
        serviceB.method4PostAuthorize();
    }

    @Test
    public void test6(){
        setupUser("ROLE_User");

        List<String> dataList = new ArrayList<>();
        dataList.add("Hi");
        dataList.add("");
        serviceB.method4PreFilter(dataList);
    }

    @Test
    public void test7(){
        setupUser("ROLE_User");
        List<String> dataList = serviceB.method4PostFilter();
        System.out.println(dataList);
    }

    @Test
    public void test8(){
        setupUser("ROLE_User");

        List<String> dataList = new ArrayList<>();
        dataList.add("Hi");

        serviceB.method4PermissionEvaluator(dataList);
    }

    private void setupUser(String... privs) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String priv : privs) {
            authorities.add(new SimpleGrantedAuthority(priv));
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("user", "password", authorities);
        securityContext.setAuthentication(authenticationToken);
    }
}
