package com.fengquanwei.hello.spring.mvc.controller;

import com.fengquanwei.hello.spring.mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    // 控制器
    @RequestMapping(value = {"/home", "/index"}, method = RequestMethod.GET)
    public String home() {
        return "home"; // 视图
    }

    // 模型1
    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public String list1(Model model) { // Model 可以使用 Map 来代替，模型数据实际存放在请求（Request 对象）中。
        model.addAttribute(buildStringList()); // 不指定 key 值时采用类型推断确定（List<String> -> stringList）
        return "list"; // 视图
    }

    // 模型2
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<String> list2() { // 视图名称根据请求路劲推断（/list -> list）
        return buildStringList(); // 将返回值放入 Model 中
    }

    // 查询参数
    @RequestMapping("/queryparameter")
    public String queryParameter(@RequestParam(value = "from", defaultValue = "1") Integer from, @RequestParam(value = "count", defaultValue = "10") Integer count, Model model) {
        model.addAttribute("stringList", buildStringList(from, from + count));
        return "list";
    }

    // 路径变量
    @RequestMapping("/pathvariable/{from}/{to}")
    public String pathVariable(@PathVariable("from") Integer from, @PathVariable Integer to, Model model) { // 若 @PathVariable 没有 value 属性的话，默认的占位符名称是方法参数名相同
        model.addAttribute("stringList", buildStringList(from, to));
        return "list";
    }

    // 展示表单
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showForm() {
        return "form";
    }

    // 提交表单
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(User user) {
        System.out.println("HomeController.submitForm(" + user + ")");
        return "redirect:/info/" + user.getName(); // 重定向防止重复提交
    }

    // 基本信息
    @RequestMapping(value = "/info/{name}", method = RequestMethod.GET)
    public String info(@PathVariable String name, Model model) {
        model.addAttribute(name);
        return "info";
    }

    // ==================== 辅助方法 ====================

    public static List<String> buildStringList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("S" + i);
        }
        return list;
    }

    public static List<String> buildStringList(int from, int to) {
        List<String> list = new ArrayList<>();
        for (int i = from; i < to; i++) {
            list.add("S" + i);
        }
        return list;
    }
}
