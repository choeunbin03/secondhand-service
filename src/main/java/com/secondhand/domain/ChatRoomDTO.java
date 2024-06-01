package com.secondhand.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ChatRoomDTO {//채팅방 리스트에서 사용되는 DTO입니다!	
	
	private int chatId; //채팅 id
	private int chatSpceId; //채팅방 id
	private int bbsId; //게시글 id
	private String chatPartner; //채팅방 상대	---> 이 부분이 ChatDTO와 다름.
	private String chatCn; //채팅 내용
	private Date sendDt; //채팅 보낸 일시
	private int chkYn; //사용자 읽음 여부.(알람 표시 관련.)  --> ChatDTO와 다름.
	// chkYn --> (로그인한 사용자가 읽지 않았을 경우만 값이1)

}
