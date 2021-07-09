package com.zlw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
//    控制视图的跳转
//    很坑，国际化的时候忘记把login的路径修改了，结果出现了500的错误
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("views/login");
        registry.addViewController("Register.html").setViewName("views/Register");
    }

//    将自己写的配置类注入到spring里面
    @Bean
    public LocaleResolver localeResolver(){
        return new MvcConfig();
    }

    //    重写拦截器
}
