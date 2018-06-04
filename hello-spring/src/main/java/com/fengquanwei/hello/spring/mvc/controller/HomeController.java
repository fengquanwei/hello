package com.fengquanwei.hello.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * HomeController
 *
 * @author fengquanwei
 * @create 2018/6/4 14:31
 **/
@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(value = {"/home", "/index"}, method = RequestMethod.GET)
    public String home() {
        return "home"; // 视图
    }

    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public String list1(Model model) { // Model 可以使用 Map 来代替
        model.addAttribute(buildStringList()); // 不指定 key 值时采用类型推断确定（stringList）
        return "list"; // 视图
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<String> list2() { // 视图名称根据请求路劲推断（list）
        return buildStringList(); // 将返回值放入 Model 中
    }

    // ==================== 辅助方法 ====================

    public static List<String> buildStringList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("S" + i);
        }
        return list;
    }
}
