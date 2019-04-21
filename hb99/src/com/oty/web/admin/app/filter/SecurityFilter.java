package com.oty.web.admin.app.filter;
 
import com.oty.constant.Constants; 
import com.oty.web.action.LoginAction; 

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 

import java.io.IOException; 
 
public class SecurityFilter implements Filter {
	 
	public void destroy() {
		
	}
 
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws ServletException, IOException { 
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.addHeader("P3P: CP", "\"CAO PSA OUR\"");   
		String home = request.getContextPath();
		String uri = request.getRequestURI();   
		HttpSession session = request.getSession(); 
		if (session.getAttribute(Constants.SESSION_ADMIN_DATA) != null) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		} 
		String loginUrl = home + "/login.html"; 	 
		if (uri.contains(loginUrl)) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		if (LoginAction.checkRememberedLoginState(request)) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			response.sendRedirect(loginUrl);
		}  
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
