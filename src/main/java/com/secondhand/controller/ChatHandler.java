package com.secondhand.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
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

import com.secondhand.domain.ChatDTO;
import com.secondhand.service.ChatService;

//@ServerEndpoint("/chatserver")
@Component
public class ChatHandler extends TextWebSocketHandler{
	
	@Inject
	private ChatService chatService;

	// 현재 채팅 서버에 접속한 클라이언트(WebSocket Session) 목록
	private List<HashMap<String, Object>> rls = new ArrayList<>(); //웹소켓 세션을 담아둘 리스트 ---roomListSessions
	//rls에 들어갈 MAP 객체 정보
	//1. chatSpceId
	//2. 채팅방 참여자 id들.(최대 2개)

    //소켓 연결 생성 후 실행 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("TextWebSocketHandler : 연결 생성!");
		//users.add(session);
		super.afterConnectionEstablished(session);
		boolean flag = false;
        String url = session.getUri().toString();
        String chatSpceId = url.split("chatSpceId=")[1];
        
        //혹시라도 이미 다른 채팅방에 id session이 저장되어있다면 삭제하는 쿼리도 있어야하나,,?
        
        int idx = rls.size(); //방의 사이즈를 조사
        if(rls.size() > 0) {
            for(int i=0; i<rls.size(); i++) {
            	String tmpCSId = (String) rls.get(i).get("chatSpceId");
            	 if(tmpCSId.equals(chatSpceId)) {
                     flag = true;
                     idx = i;
                     break;
                 }
            }
        }
        
        if(flag) {//이미 존재하는 방이라면 세션만 추가한다.
        	HashMap<String, Object> map = rls.get(idx);
            map.put(session.getId(), session);
        }else { //최초 생성하는 방이라면 방번호와 세션을 추가한다.
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("chatSpceId", chatSpceId);
            map.put(session.getId(), session);
            rls.add(map);
        }
        
        //세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
        //근데 내 코드에서는 굳이? 누가 참여했는지 알릴 필요가 없음
        JSONObject obj = new JSONObject();
        obj.put("type", "getId");
        obj.put("sessionId", session.getId());
        //session.sendMessage(new TextMessage(obj.toString()));

	}
	
	@Override
    //메시지 수신 후 실행 메서드
	protected void handleTextMessage(WebSocketSession session, TextMessage object) throws Exception {
		//테스트 log 출력.
		System.out.println("TextWebSocketHandler : 메시지 수신!");
		System.out.println("메시지 : " + object.getPayload());
		
		//JSON형태의 String메시지를 받아옴
		String tmp = object.getPayload();
		//JSON데이터를 JSONObject로 파싱
		JSONObject jsonObject = new JSONObject(tmp);
		
		//채팅방 번호 가져오기
		int chatSpceId = Integer.parseInt((String)jsonObject.get("chatSpceId"));
				
		//chatId 가져오기
		int maxChatId = chatService.getMaxChatId();
		
		//날짜 정보 데이터타입 변환.
		String dateString = (String)jsonObject.get("sendDt");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sendDt = format.parse(dateString);
		
		//idntyYn 설정.
		int idntyYn = 1;
		for(int i = 0; i < rls.size(); i++) {
			int tmpChatSpceId = Integer.parseInt((String)rls.get(i).get("chatSpceId"));
			if(tmpChatSpceId == chatSpceId) {
				if(rls.get(i).size() == 3) {
					idntyYn = 0;
				}
			}
		}
		
		//전달할 jsonObject에 idntyYn정보 추가
		jsonObject.put("idntyYn", idntyYn);
		
		//chatDTO에 정보 저장.
		ChatDTO chatDto = ChatDTO.builder()
						.chatId(maxChatId+1)
						.chatSpceId(chatSpceId)//javascript에서 넘겨주기
						.sendId((String)jsonObject.get("sendId"))//"
						.rcvId((String)jsonObject.get("rcvId"))//"
						.chatCn((String)jsonObject.get("chatCn"))//"
						.sendDt(sendDt)//"
						.idntyYn(idntyYn)
						.build();
		//db에 데이터 저장.
		chatService.regiChat(chatDto);
		
		HashMap<String, Object> temp = new HashMap<String, Object>();
		
		if(rls.size() > 0) {
			 for(int i=0; i<rls.size(); i++) {
				 int tmpChatSpceId = Integer.parseInt((String) rls.get(i).get("chatSpceId")); //세션리스트의 저장된 방번호를 가져와서
				 if(tmpChatSpceId == chatSpceId) {//같은값의 방이 존재한다면
					 temp = rls.get(i); //해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
					 break;
				 }
			 }
			 for(String k : temp.keySet()) {
				 if(k.equals("chatSpceId")) { //다만 방번호일 경우에는 건너뛴다.
					 continue;
				 }
				 
				 WebSocketSession wss = (WebSocketSession) temp.get(k);
				 if(wss != null) {
                     try {
                         TextMessage textMessage = new TextMessage(jsonObject.toString());
                         wss.sendMessage(textMessage);

                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
			 }
		}
		
	}

	//연결 해제 후 실행 메서드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("TextWebSocketHandler : 연결 종료!");
		System.out.println(status);
		if(rls.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
            for(int i=0; i<rls.size(); i++) {
                rls.get(i).remove(session.getId());
            }
        }
        super.afterConnectionClosed(session, status);
	}
	
	
}
