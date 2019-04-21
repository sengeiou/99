package com.oty.web.app.listener; 

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;  

import com.oty.constant.Constants;
 
public class PrepareGlobalDataListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext(); 
		Constants.HOME = sc.getContextPath(); 
		sc.setAttribute("home", Constants.HOME);	
		Constants.REALPATH = sc.getRealPath("//");		 
	}

	public void contextDestroyed(ServletContextEvent event) {
		
	}
	
}
