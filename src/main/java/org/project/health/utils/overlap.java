package org.project.health.utils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class overlap implements HttpSessionListener{
	HttpSession session;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// 세션 생성
		HttpSession session = se.getSession();
		System.out.println("세션 생성");
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// 세션 파괴
		HttpSession session = se.getSession();
		System.out.println("세션 파괴");
	}
	
	
}
