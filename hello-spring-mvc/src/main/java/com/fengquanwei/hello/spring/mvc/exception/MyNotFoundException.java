package com.fengquanwei.hello.spring.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 将异常映射为 HTTP 状态码
 *
 * @author fengquanwei
 * @create 2018/6/6 10:02
 **/
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Name Not Found")
public class MyNotFoundException extends RuntimeException {
}
