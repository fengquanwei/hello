package com.fengquanwei.hello.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * HomeController
 *
 * @author fengquanwei
 * @create 2018/6/4 14:31
 **/
@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(value = {"/home", "/index"}, method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
