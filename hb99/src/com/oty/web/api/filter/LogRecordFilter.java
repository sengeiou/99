package com.oty.web.api.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogRecordFilter implements Filter {
	
    private static final Logger logger = LoggerFactory.getLogger(LogRecordFilter.class);

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.debug("-------------------------------------------start----------------------------------------------------------");
        logger.debug("Session ID :" + request.getSession().getId());

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.debug("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString); 
        // 获取所有的请求数据
        Enumeration<?> e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String paramName = (String) e.nextElement();
            String value2 = request.getParameter(paramName);
            logger.debug(paramName + "=" + value2);
        } 
        // 拿到所有请求头
        Enumeration<?> e1 = request.getHeaderNames();
        while (e1.hasMoreElements()) {
            String headerName = (String) e1.nextElement();
            String headValue = request.getHeader(headerName);
            logger.debug(headerName + "=" + headValue);
        } 
        logger.debug("--------------------------------------------end---------------------------------------------------------"); 
        chain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
