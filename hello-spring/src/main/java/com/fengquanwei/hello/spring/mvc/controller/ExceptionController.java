package com.fengquanwei.hello.spring.mvc.controller;

import com.fengquanwei.hello.spring.mvc.exception.MyNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ExceptionController
 *
 * @author fengquanwei
 * @create 2018/6/5 14:07
 **/
@Controller
@RequestMapping("/exception")
public class ExceptionController {
    // 将异常映射为 HTTP 状态码
    @RequestMapping(value = "/info/{name}", method = RequestMethod.GET)
    public String info(@PathVariable String name, Model model) {
        if (!"Lask".equals(name)) {
            throw new MyNotFoundException();
        }

        model.addAttribute(name);
        return "info";
    }
}
