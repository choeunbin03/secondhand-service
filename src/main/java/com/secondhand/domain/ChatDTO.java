package com.secondhand.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDTO {
	
	private int chatId; //채팅 id
	private int chatSpceId; //채팅방 id
	private int bbsId; //게시글 id
	private String sendId; //보낸 사람 아이디
	private String rcvId; //받는 사람 아이디
	private String chatCn; //채팅 내용
	private Date sendDt; //채팅 보낸 일시
	private int atchFileNo; //첨부파일 번호 구현 가능할지 모르겠음.
	private int idntyYn; //읽음 여부
	
	

}
