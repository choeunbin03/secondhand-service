package com.secondhand.dao;

import java.util.List;
import java.util.Map;

import com.secondhand.domain.ChatDTO;
import com.secondhand.domain.ChatRoomDTO;

public interface ChatDAO {
	
	//채팅방 리스트 가져오기
	public List<ChatRoomDTO> getchatRoomList(Map<String, Object> params);
	//선택된 채팅방 채팅 내역 가져오기.
	public List<ChatDTO> getChat(Map<String, Object> params);
	//사용자가 채팅방 선택 시 읽음 여부 업데이트
	public void updateIdntyYn(Map<String, Object> params);
	//chatId 최대값 가져오기
	public int getMaxChatId();
	//채팅 상대 아이디 가져오기
	public String getChatPartnerId(Map<String, Object> params);
	//채팅 등록
	public void regiChat(ChatDTO chatDto);
	//채팅방 존재 확인 return 1:존재 / 0:없음
//	public int chatRoomExists(Map<String, Object> params);
	//채팅하기 버튼 클릭 시 이미 존재하는 채팅방일 경우 채팅방 번호 가져오기
	public int getChatSpceId(Map<String, Object> params);
	//max chatSpceId 가져오기
	public int getMaxChatSpceId();
	//채팅 상대 id 가져오기
	public String getPartnerId(int bbsId);
	//채팅방 새로 생성
	public void createChatRoom(Map<String, Object> params);
	//채팅방 게시글 id 가져오기
	public int getBbsId(Map<String, Object> params);//사실 상 chat_spce_id만 사용
	//게시글 삭제 시 chat room table의 게시글 번호 0으로 업데이트
	public void deleteBbs(int bbsId);

}
