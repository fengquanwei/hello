package com.fengquanwei.hello.spring.mvc.controller;

import com.fengquanwei.hello.spring.mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        model.addAttribute("name", name);
        return "info";
    }

    /**
     * 消息转换器
     * consumes：Content-Type
     * produces：Accept
     */
    @RequestMapping(value = "/mc/user", consumes = "application/json", produces = "application/json")
    public @ResponseBody User messageConversion(@RequestBody User user) {
        return user;
    }
}
