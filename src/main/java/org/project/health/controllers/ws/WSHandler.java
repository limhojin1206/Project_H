package org.project.health.controllers.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ws")
public class WSHandler extends TextWebSocketHandler {
	/*
	 * 	소켓 사용법
	 * 	mode : confirm / alert
	 * 	content : 상대 창에 보일 메세지
	 * 	href : 확인 눌렀을때 경로
	 * 
	 *  예 :
	 *  mode : confirm
	 *  content : 확인하시겠습니까?
	 *  href : "/memo/receivebox"
	 *  
	 *  mode : alert
	 *  content : "map.receiver" + 님께서 요청을 수락하셨습니다.
	 */
	
	@Autowired
	ObjectMapper mapper;
	
	Map<String, WebSocketSession> users = new HashMap();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String, Object> hs = session.getAttributes();
		String id = (String)((Map)hs.get("auth")).get("ID");
		users.put(id, session);
		//System.out.println("현재 접속 인원 " + users.toString());
	}
	
	public void sendMessage(Map map) {
		if(users.containsKey(map.get("receiver"))) {
			try {
				String json = mapper.writeValueAsString(map);
				users.get(map.get("receiver")).sendMessage(new TextMessage(json));
			} catch (IOException e) {
			}
		}
	}
	/*
	public void confirmMessage(Map map) {
		if(users.containsKey(map.get("sender"))) {
			try {
				String json = mapper.writeValueAsString(map);
				users.get(map.get("sender")).sendMessage(new TextMessage(json));
			} catch (IOException e) {
			}
		}
	}
	*/
	
}
