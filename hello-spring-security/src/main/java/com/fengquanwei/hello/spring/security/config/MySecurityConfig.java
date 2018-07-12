package com.fengquanwei.hello.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * MySecurityConfig
 *
 * @author fengquanwei
 * @create 2018/7/12 11:35
 **/
@Configuration
@EnableWebSecurity // 启用 Web 安全性
//@EnableWebMvcSecurity // 启用 Spring MVC 安全性
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 配置 Spring Security 的 Filter 链
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 配置如何通过拦截器保护请求
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                formLogin() // 启用默认的登录页，不重写本方法默认提供，重写本方法需要这样指定
                .and()

                .httpBasic().realmName("localhost") // 启用 HTTP Basic 认证
                .and()

                .rememberMe().tokenValiditySeconds(60 * 60).key("myKey") // 启用 Remember-me 功能，登录请求中必须包含 remember-me 参数（可以用复选框实现）
                .and()

                .logout().logoutUrl("/logout").logoutSuccessUrl("/") // 配置退出地址以及退出后的重定向地址
                .and()

                .authorizeRequests()
                .antMatchers("/my/**").authenticated() // Ant 通配符
                .regexMatchers("/admin/.*").hasRole("Admin") // 正则表达式
                .antMatchers(HttpMethod.POST, "/post/**").access("hasRole('User') and hasIpAddress('127.0.0.1')") // SpEL
                .anyRequest().permitAll()
                .and()

                .requiresChannel()
                .antMatchers("/channel/**").requiresSecure() // 需要 HTTPS，自动将请求重定向到 HTTPS
                .antMatchers("/nohttps").requiresInsecure() // 不需要 HTTPS，自动将请求重定向到 HTTP
                .and()

                .csrf().disable(); // 禁用 CSRF 防护功能，默认开启，用于防止跨站请求，不建议禁用
    }

    /**
     * 配置 user-detail 服务
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 基于内存进行认证【Option1:1】
        auth.inMemoryAuthentication()
                .withUser("Lask").password("Lask").roles("User").and()
                .withUser("Lynn").password("Lynn").roles("User", "Admin");

        // 基于数据库进行认证【Option1:2】未测试
//        auth.jdbcAuthentication()
//                .dataSource(null) // 未配置 DataSource
//                .usersByUsernameQuery("select username, passport, enabled from security_user where username = ?") // 认证查询
//                .authoritiesByUsernameQuery("select username, authority from security_authority where username = ?") // 权限查询
//                .groupAuthoritiesByUsername("select g.id, g.group_name, ga.authority " +
//                        "from security_group g, security_group_members gm, security_group_authority ga " +
//                        "where gm.username = ? and g.id = ga.group_id and g.id = gm.gorup_id") // 群组查询
//                .passwordEncoder(new StandardPasswordEncoder("secret")); // 密码转码器

        // 基于 LDAP 进行认证【Option1:3】未测试
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("(member={0})")
////                .contextSource().url("ldap://localhost:33389/dc=localhost,dc=com")
////                .contextSource().root("dc=localhost,dc=com").ldif("classpath:users.ldif")
//                .passwordCompare()
//                .passwordAttribute("password")
//                .passwordEncoder(new Md5PasswordEncoder());

        // 基于自定义的用户服务认证【Option1:4】未测试
//        auth.userDetailsService(new MyUserDetailsService());
    }
}
