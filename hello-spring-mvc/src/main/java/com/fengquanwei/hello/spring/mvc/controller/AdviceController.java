package com.fengquanwei.hello.spring.mvc.controller;

import com.fengquanwei.hello.spring.mvc.exception.MyAdviceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 控制器通知类中的方法会运用到应用中所有控制器中带有 @RequestMapping 注解的方法上
 *
 * @author fengquanwei
 * @create 2018/6/6 14:46
 **/
@ControllerAdvice // 控制器通知
public class AdviceController {
    @ExceptionHandler(MyAdviceException.class)
    public String handleMyAdviceException() {
        return "error";
    }
}
