package com.fengquanwei.hello.spring.mvc.controller;

import com.fengquanwei.hello.spring.mvc.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 为控制器默认设置消息转换
 *
 * @author fengquanwei
 * @create 2018/8/1 09:55
 **/
@RestController
@RequestMapping("/rest")
public class MyRestController {
    /**
     * 已设置默认的消息转换器
     */
    @RequestMapping(value = "/user", consumes = "application/json", produces = "application/json")
    public User messageConversion(@RequestBody User user) {
        return user;
    }
}
