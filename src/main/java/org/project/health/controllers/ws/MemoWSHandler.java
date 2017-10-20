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

@Component("memows")
public class MemoWSHandler extends TextWebSocketHandler {
	@Autowired
	ObjectMapper mapper;
	
	Map<String, WebSocketSession> users = new HashMap();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String, Object> hs = session.getAttributes();
		String id = (String)((Map)hs.get("auth")).get("ID");
		users.put(id, session);
	}
	
	public void sendMessage(Map map) {
		if(users.containsKey(map.get("receiver"))) {
			try {
				Map m = new HashMap();
					m.put("mode", "memo");
					m.put("send",map.get("sender"));
					m.put("msg","");
				String json = mapper.writeValueAsString(m);
				users.get(map.get("receiver")).sendMessage(new TextMessage(json));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
