package com.secondhand.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//@ServerEndpoint("/chatserver")
@Component
public class ChatHandler extends TextWebSocketHandler{

	// 현재 채팅 서버에 접속한 클라이언트(WebSocket Session) 목록
	
	private List<WebSocketSession> users;//static 안 붙여도 되나??
	private Map<String, Object> userMap;
	
	public ChatHandler() {
		users= new ArrayList<WebSocketSession>();
		userMap = new HashMap<String,Object>();
	}

    //소켓 연결 생성 후 실행 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("TextWebSocketHandler : 연결 생성!");
		users.add(session);
	}
	
	@Override
    //메시지 수신 후 실행 메서드
	protected void handleTextMessage(WebSocketSession session, TextMessage object) throws Exception {
		System.out.println("TextWebSocketHandler : 메시지 수신!");
		System.out.println("메시지 : " + object.getPayload());
		JSONObject jsonObject = new JSONObject(object.getPayload());
		String type = jsonObject.getString("type");
	
		if(type != null && type.equals("register") ) {
			//등록 요청 메시지
			String sendId = jsonObject.getString("sendId");
			//아이디랑 Session이랑 매핑 >>> Map
			userMap.put(sendId, session);
		}else {
			//채팅 메시지 : 상대방 아이디를 포함해서 메시지를 보낼것이기 때문에
			//Map에서 상대방 아이디에 해당하는 WebSocket 꺼내와서 메시지 전송
			String rcvId = jsonObject.getString("rcvId");
			WebSocketSession ws = (WebSocketSession)userMap.get(rcvId);
			String msg = jsonObject.getString("message");
			String sendDt = jsonObject.getString("sendDt");
			if(ws !=null ) {
				//ws.sendMessage(new TextMessage(msg+sendDt));//이게 맞나 확인하기
				ws.sendMessage(object);
				//**************************여기 수정 먼저~!
				//내가 봤을 땐
				//msg+sendDt한 거 json형태 객체로 변환해서(json 변수명 object)
				//ws.sendMessage(new TextMessage(json객체)); 이렇게 넘겨야할듯 그리고 javascript에서 변환
				//db에 메세지 저장하는 메소드.(json 타입 변경하는 메소드를 따로 service에 생성 후~)
			}
		}
	}

	//연결 해제 후 실행 메서드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("TextWebSocketHandler : 연결 종료!");
		users.remove(session);
	}
	
}
