package com.dhcc.piccbid.utils.session;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * <p>标题: SessionContext.java</p>
 * <p>业务描述:公共卫生组</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2013</p>
 * @author gzw
 * @date 2016年7月25日
 * @version V1.0 
 */
public class SessionContext {
	private static SessionContext instance;  
	private HashMap<String, HttpSession> sessionMap;  
	  
	private SessionContext() {  
		sessionMap = new HashMap<String, HttpSession>();  
	}  
	  
	public static SessionContext getInstance() {  
		if (instance == null) {  
			instance = new SessionContext();  
		}  
		return instance;  
	}  
	  
	public synchronized void AddSession(HttpSession session) {  
		if (session != null) {  
			sessionMap.put(session.getId(), session);  
		}  
	}  
	  
	public synchronized void DelSession(HttpSession session) {  
		if (session != null) {  
			sessionMap.remove(session.getId());  
		}  
	}  
	  
	public synchronized HttpSession getSession(String session_id) {  
		if (session_id == null) return null;  
		return (HttpSession) sessionMap.get(session_id);  
	} 
}