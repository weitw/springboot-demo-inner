package com.weitw.study.sbt.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;  
import java.util.UUID;

import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@WebFilter
public class MDCFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化代码（如果需要的话）  
    }
  
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 生成UUID并放入MDC
        String requestId = IdUtil.fastSimpleUUID();
        MDC.put("requestId", requestId);
  
        try {
            // 继续过滤器链
            chain.doFilter(request, response);
        } finally {
            // 清理MDC
            MDC.remove("requestId");
        }
    }
  
    @Override
    public void destroy() {
        // 销毁代码（如果需要的话）
    }
}