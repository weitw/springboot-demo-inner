package com.weitw.study.sbt.filter;

import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uuid = IdUtil.simpleUUID();
        System.out.println("requestId=" + uuid);
        MDC.put("requestId", uuid);
        try {
            System.out.println("xss过滤器执行前的逻辑运行");
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("xss过滤器执行后的逻辑运行");
        } finally {
            MDC.remove("requestId");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
