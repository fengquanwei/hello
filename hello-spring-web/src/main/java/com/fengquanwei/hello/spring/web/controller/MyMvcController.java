package com.fengquanwei.hello.spring.web.controller;

import com.fengquanwei.hello.spring.web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/cn/{name}")
    public String contentNegotiating(@PathVariable("name") String name, Model model) {
        model.addAttribute("user", name);
        return "hello";
    }

    /**
     * 消息转换器
     * consumes：Content-Type
     * produces：Accept
     */
    @RequestMapping(value = "/mc/user", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public User messageConversion(@RequestBody User user) {
        return user;
    }
}
