package com.fengquanwei.hello.spring.web.config;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Locale;

/**
 * JsonViewResolver
 *
 * @author fengquanwei
 * @create 2018/7/31 16:42
 **/
public class JsonViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        mappingJackson2JsonView.setPrettyPrint(true);
        return mappingJackson2JsonView;
    }
}
