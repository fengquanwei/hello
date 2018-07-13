package com.fengquanwei.hello.spring.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MyController
 *
 * @author fengquanwei
 * @create 2018/7/12 17:40
 **/
@Controller
@RequestMapping("/my")
public class MyController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello";
    }
}
