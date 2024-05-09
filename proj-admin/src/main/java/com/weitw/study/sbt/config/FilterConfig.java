package com.weitw.study.sbt.config;

import com.weitw.study.sbt.filter.MDCFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
  
@Configuration  
public class FilterConfig {  
  
    @Bean  
    public FilterRegistrationBean<MDCFilter> mdcFilterRegistration() {
        FilterRegistrationBean<MDCFilter> registration = new FilterRegistrationBean<>();  
        registration.setFilter(new MDCFilter());  
        registration.addUrlPatterns("/*"); // 匹配所有请求  
        registration.setName("mdcFilter");  
        registration.setOrder(1); // 设置过滤器的顺序，如果需要的话  
        return registration;  
    }  
}