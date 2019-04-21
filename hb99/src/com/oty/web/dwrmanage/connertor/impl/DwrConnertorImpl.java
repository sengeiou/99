package com.oty.web.dwrmanage.connertor.impl;

import org.directwebremoting.ScriptSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oty.constant.Constants;
import com.oty.server.model.MessageWrapper;
import com.oty.server.model.Session;
import com.oty.server.session.SessionManager;
import com.oty.web.dwrmanage.connertor.DwrConnertor;

@Service("dwrConnertorImpl")
public class DwrConnertorImpl implements DwrConnertor {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SessionManager sessionManager;

	public void close(ScriptSession scriptSession) {
		String sessionId = (String) scriptSession.getAttribute(Constants.SessionConfig.SESSION_KEY);
		try {
			String nid = scriptSession.getId();
			Session session = sessionManager.getSession(sessionId);
			if (session != null) {
				sessionManager.removeSession(sessionId, nid);

				log.info("dwrconnector close sessionId -> " + sessionId + " success ");
			}
		} catch (Exception e) {
			log.error("dwrconnector close sessionId -->" + sessionId + "  Exception.", e);
			throw new RuntimeException(e.getCause());
		}
	}

	public void connect(ScriptSession scriptSession, String sessionid) {
		try {
			log.info("dwrconnector connect sessionId -> " + sessionid);
			sessionManager.createSession(scriptSession, sessionid);
		} catch (Exception e) {
			log.error("dwrconnector connect  Exception.", e);
		}
	}

	public void pushMessage(String sessionId, MessageWrapper wrapper) throws RuntimeException {
		Session session = sessionManager.getSession(sessionId);
		session.write(wrapper.getBody());
	}

}
