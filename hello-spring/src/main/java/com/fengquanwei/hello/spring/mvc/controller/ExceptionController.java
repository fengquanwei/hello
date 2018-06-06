package com.fengquanwei.hello.spring.mvc.controller;

import com.fengquanwei.hello.spring.mvc.exception.MyAdviceException;
import com.fengquanwei.hello.spring.mvc.exception.MyDuplicateException;
import com.fengquanwei.hello.spring.mvc.exception.MyNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    @RequestMapping(value = "/info1/{name}", method = RequestMethod.GET)
    public String mapping(@PathVariable String name, Model model) {
        if (!"Lask".equals(name)) {
            throw new MyNotFoundException();
        }

        model.addAttribute("user", name);
        return "info";
    }

    // 使用异常处理器来处理异常
    @RequestMapping(value = "/info2/{name}", method = RequestMethod.GET)
    public String handle(@PathVariable String name, Model model) {
        if ("Lask".equals(name)) {
            throw new MyDuplicateException();
        } else if ("Advice".equals(name)) {
            throw new MyAdviceException();
        }

        model.addAttribute("user", name);
        return "info";
    }

    // 异常处理器（只能处理本控制器中的所有指定异常，想对所有控制器指定异常统一处理请使用控制器通知，或者使用继承将异常处理放在基类中）
    @ExceptionHandler(MyDuplicateException.class)
    public String handleMyDuplicateException() {
        return "error";
    }
}
