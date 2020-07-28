package com.dhcc.piccbid.utils.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//session监听

@WebListener
public class SessionManage implements HttpSessionListener {
	private SessionContext sessionContext = SessionContext.getInstance();

	/**
	 * userId和seeionId的绑定关系
	 */
	public static Map<String, String> USERID_SESSIONID = new HashMap<String, String>();

	/**
	 * sessionId和User绑定关系
	 */
	public void sessionCreated(HttpSessionEvent event) {
		sessionContext.AddSession(event.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(event.getSession().getServletContext());
		//清除sessionMap中的session
		HttpSession session = event.getSession();  
		sessionContext.DelSession(session);
		session.invalidate();
	}
}
