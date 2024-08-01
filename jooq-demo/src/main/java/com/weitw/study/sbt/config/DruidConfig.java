//package com.weitw.study.sbt.config;
//
//import com.alibaba.druid.support.http.StatViewServlet;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DruidConfig {
//
//    @Bean
//    public ServletRegistrationBean<StatViewServlet> druidServlet() {
//        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//        servletRegistrationBean.addInitParameter("loginUsername", "admin");
//        servletRegistrationBean.addInitParameter("loginPassword", "123456");
//        servletRegistrationBean.addInitParameter("allow", "127.0.0.1"); // 允许访问的IP
//        return servletRegistrationBean;
//    }
//}
