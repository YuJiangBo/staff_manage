package com.yu.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {

//  解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
//        获取请求中的语言参数
        String language = request.getParameter("l");
//        获取默认的 如果没有语言参数就用默认的
        Locale locale = Locale.getDefault();
//        判断有没有语言参数
        if(!StringUtils.isEmpty(language)){
//            分割 语言、地区
            String[] split = language.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
