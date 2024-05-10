package com.weitw.study.sbt.filter;

import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Lists;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@WebFilter(filterName = "logFilter", urlPatterns = "/filter-demo/*")
public class LogFilter implements Filter {

//    private final List<String> whiteList = Collections.singletonList("/index");
//    private final List<String> blackList = Collections.singletonList("/filter");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uuid = IdUtil.simpleUUID();
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        String requestURI = httpRequest.getRequestURI();
//        for (String s : whiteList) {
//            if (requestURI.contains(s)) {
//                System.out.println("url=" + requestURI + " 在白名单内，不需要过滤");
//                filterChain.doFilter(servletRequest, servletResponse);
//                return;
//            }
//        }

        System.out.println("requestId=" + uuid);
        MDC.put("requestId", uuid);
        try {
            System.out.println("log过滤器执行前的逻辑运行");
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("log过滤器执行后的逻辑运行");
        } finally {
            MDC.remove("requestId");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
