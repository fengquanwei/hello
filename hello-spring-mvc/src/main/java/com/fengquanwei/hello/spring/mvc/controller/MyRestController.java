package com.fengquanwei.hello.spring.mvc.controller;

import com.fengquanwei.hello.spring.mvc.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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

    /**
     * 响应元数据（包含响应头信息，状态码以及负载）
     */
    @RequestMapping(value = "/entity", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> responseEntity(@RequestBody User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("http://localhost:8080/hello"));

        return new ResponseEntity<User>(user, httpHeaders, HttpStatus.NOT_FOUND);
    }
}
