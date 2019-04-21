package com.oty.web.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse; 

import pub.spring.RequestScopeMessageSource; 
 
public class PrepareRequestDataFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {
	 
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
						 FilterChain chain) throws IOException, ServletException {  
		try {
			chain.doFilter(servletRequest, servletResponse);
		} finally { 
			RequestScopeMessageSource.clear();
		}
	}

	public void destroy() {
		 
	}
}
