package com.fengquanwei.hello.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 跨重定向请求传递数据
 * Model 中的数据是 Request Attribute
 *
 * @author fengquanwei
 * @create 2018/6/6 15:06
 **/
@Controller
@RequestMapping("/redirect")
public class RedirectController {
    // http://localhost:8081/redirect/forward2param?name=Lask
    // Request Parameter: name = Lask【来自 QueryString】
    // 请求转发到 param（同一个请求）
    // Request Attribute: user=Lask【addAttribute 手动添加】
    // 没有 Request Attribute: name = Lask，就没添加过
    @RequestMapping(value = "/forward2param", method = RequestMethod.GET)
    public String forward2param(@RequestParam String name) {
        System.out.println("RedirectController.forward2param(" + name + ")");
        return "forward:/redirect/param";
    }

    // http://localhost:8081/redirect/forward2path?name=Lask
    // Request Parameter: name = Lask【来自 QueryString】
    // 请求转发到 path（同一个请求）
    // Request Attribute: name=Lask【PathVariable 自动添加】
    // Request Attribute: user=Lask【addAttribute 手动添加】
    @RequestMapping(value = "/forward2path", method = RequestMethod.GET)
    public String forward2path(@RequestParam String name) { // Request Parameter: name = Lask【来自 QueryString】
        System.out.println("RedirectController.forward2path(" + name + ")");
        return "forward:/redirect/path/" + name;
    }

    // http://localhost:8081/redirect/redirect2param?name=Lask
    // Request Parameter: name = Lask【来自 QueryString】
    // Request Attribute: name=Lask【addAttribute 手动添加】
    // 重定向到 param【一个新请求】
    // Request Attribute: user=Lask【addAttribute 手动添加】
    // 没有 Request Attribute: name=Lask，那是老请求的，新请求没有添加
    @RequestMapping(value = "/redirect2param", method = RequestMethod.GET)
    public String redirect2param(@RequestParam String name, Model model) {
        System.out.println("RedirectController.redirect2param(" + name + ")");
        model.addAttribute("name", name); // 不是同一个请求啦，已经没有 name 参数了，所以要透传过去（作为查询参数拼接到 URL 后面）
        return "redirect:/redirect/param";
    }

    // http://localhost:8081/redirect/redirect2path?name=Lask
    // Request Parameter: name = Lask【来自 QueryString】
    // 重定向到 path【一个新请求】
    // Request Attribute: name=Lask【PathVariable 自动添加】
    // Request Attribute: user=Lask【addAttribute 手动添加】
    @RequestMapping(value = "/redirect2path", method = RequestMethod.GET)
    public String redirect2path(@RequestParam String name) {
        System.out.println("RedirectController.redirect2path(" + name + ")");
        return "redirect:/redirect/path/" + name;
    }

    // http://localhost:8081/redirect/placeholder?name=Lask
    // 使用占位符（占位符内的特殊字符会自动转义）
    @RequestMapping(value = "/placeholder", method = RequestMethod.GET)
    public String placeholder(@RequestParam String name, Model model) {
        System.out.println("RedirectController.placeholder(" + name + ")");
        model.addAttribute("name", name);
        return "redirect:/redirect/path/{name}";
    }

    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public String param(@RequestParam String name, Model model) {
        model.addAttribute("user", name);
        return "info";
    }

    @RequestMapping(value = "/path/{name}", method = RequestMethod.GET)
    public String path(@PathVariable String name, Model model) {
        model.addAttribute("user", name);
        return "info";
    }
}
