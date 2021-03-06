package com.fengquanwei.hello.tomcat.connector2.core;

import com.fengquanwei.hello.tomcat.connector2.connector.HttpRequest;
import com.fengquanwei.hello.tomcat.connector2.connector.HttpResponse;

import java.io.IOException;

/**
 * 静态资源处理器
 *
 * @author fengquanwei
 * @create 2017/8/28 14:11
 **/
public class StaticResourceProcessor {
    public void process(HttpRequest request, HttpResponse response) {
        try {
            response.sendStaticResource();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
