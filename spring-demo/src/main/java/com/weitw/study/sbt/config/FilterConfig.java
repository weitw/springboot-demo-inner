package com.weitw.study.sbt.config;

import com.weitw.study.sbt.filter.LogFilter;
import com.weitw.study.sbt.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 拦截器配置
 * @author weitw
 * @date 2024/5/10 10:28
 */

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LogFilter> registFilter1(){
        FilterRegistrationBean<LogFilter>  registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("Filter1");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<XssFilter> registFilter2(){
        FilterRegistrationBean<XssFilter>  registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("Filter1");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}