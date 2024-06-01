package com.secondhand.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
	
	private int chatId; //채팅 id
	private int chatSpceId; //채팅방 id
	private String sendId; //보낸 사람 아이디
	private String rcvId; //받는 사람 아이디
	private String chatCn; //채팅 내용
	private Date sendDt; //채팅 보낸 일시
	private int idntyYn; //읽음 여부
	
	

}
