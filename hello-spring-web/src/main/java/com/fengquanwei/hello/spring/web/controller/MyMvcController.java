package com.fengquanwei.hello.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC 控制器
 *
 * @author fengquanwei
 * @create 2018/7/31 14:50
 **/
@Controller
@RequestMapping("/mvc")
public class MyMvcController {
    /**
     * 内容协商
     * http://localhost:8081/mvc/hello/Lask.html
     * http://localhost:8081/mvc/hello/Lask.json
     */
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name, Model model) {
        model.addAttribute("user", name);
        return "hello";
    }
}
