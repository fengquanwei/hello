package com.fengquanwei.hello.spring.mvc.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * HomeControllerTest
 *
 * @author fengquanwei
 * @create 2018/6/4 14:45
 **/
public class HomeControllerTest {
    @Test
    public void testHome() throws Exception {
        // 对 "/home" 执行 GET 请求，预期得到 "home" 视图
        mockMvc(new HomeController()).perform(MockMvcRequestBuilders.get("/home")).andExpect(MockMvcResultMatchers.view().name("home"));
    }

    private MockMvc mockMvc(Object... controllers) {
        // 虽然在 MyWebConfig 中定义了视图解析器，但并未生效（可能是 MockMvc 的 bug）
        // 在此重新定义，不然当 path = view 的时候默认的视图解析器会导致 Circular view path 异常
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setExposeContextBeansAsAttributes(true);

        return MockMvcBuilders.standaloneSetup(controllers).setViewResolvers(viewResolver).build();
    }
}
