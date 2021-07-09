package com.zlw.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//国际化类，实现国际化的开发

public class MvcConfig implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language=request.getParameter("l");

//        如果没有就是默认的语言
        Locale locale=Locale.getDefault();

//        如果请求资源携带了连接请求
        if(StringUtils.hasText(language)){
//            zh_CN
            String[] split=language.split("_");
//              国家，地区
            locale=new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }

}
