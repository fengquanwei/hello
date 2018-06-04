package com.fengquanwei.hello.spring.mvc.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * HomeControllerTest
 *
 * @author fengquanwei
 * @create 2018/6/4 14:45
 **/
public class HomeControllerTest {
    @Test
    public void testHome() throws Exception {
        mockMvc(new HomeController()).perform(MockMvcRequestBuilders.get("/home")) // 对路径 /home 发起 GET 请求
                .andExpect(MockMvcResultMatchers.view().name("home")); // 断言结果视图是 home
    }

    @Test
    public void testList() throws Exception {
        mockMvc(new HomeController()).perform(MockMvcRequestBuilders.get("/list")) // 对路径 /list 发起 GET 请求
                .andExpect(MockMvcResultMatchers.view().name("list")) // 断言结果视图是 list
                .andExpect(MockMvcResultMatchers.model().attributeExists("stringList")) // 断言存在模型属性 stringList
                .andExpect(MockMvcResultMatchers.model().attribute("stringList", buildStringList())); // 断言模型属性的值
    }

    // ==================== 辅助方法 ====================

    private MockMvc mockMvc(Object... controllers) {
        // 虽然在 MyWebConfig 中定义了视图解析器，但并未生效（可能是 MockMvc 的 bug）
        // 在此重新定义，不然当 path = view 的时候默认的视图解析器会导致 Circular view path 异常
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setExposeContextBeansAsAttributes(true);

        return MockMvcBuilders.standaloneSetup(controllers).setViewResolvers(viewResolver).build();
    }

    public static List<String> buildStringList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("S" + i);
        }
        return list;
    }
}
