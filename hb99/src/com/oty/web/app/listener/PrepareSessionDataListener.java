package com.oty.web.app.listener;

import pub.web.PageDataStore;
import pub.web.RestoreRedirectedRequestDataFilter;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
 
public class PrepareSessionDataListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		final HttpSession session = event.getSession();
		session.setAttribute(RestoreRedirectedRequestDataFilter.REQUEST_DATA_MAP_FOR_REDIRECT,
			new HashMap<String, Object>());

		PageDataStore.initialize(session);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		
	}
}
