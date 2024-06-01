package com.secondhand.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.secondhand.dao.ChatDAO;
import com.secondhand.domain.ChatDTO;
import com.secondhand.domain.ChatRoomDTO;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Inject
	private ChatDAO chatDao;

/*	@Override
	public List<ChatRoomDTO> getchatRoomList(String mbrId) {
		List<ChatRoomDTO> chatRoomList = chatDao.getchatRoomList(mbrId);
		return chatRoomList;
	}*/

	@Override
	public List<ChatDTO> getChat(Map<String, Object> params) {
		List<ChatDTO> chatView = chatDao.getChat(params);
		return chatView;
	}
	
	@Override
	public List<ChatRoomDTO> getchatRoomList(Map<String, Object> params) {
		List<ChatRoomDTO> chatRoomList = chatDao.getchatRoomList(params);
		return chatRoomList;
	}

	@Override
	public void updateIdntyYn(Map<String, Object> params) {
		chatDao.updateIdntyYn(params);		
	}

	@Override
	public int getMaxChatId() {
		int maxChatId = chatDao.getMaxChatId();
		return maxChatId;
	}
	
	@Override
	public String getChatPartnerId(Map<String, Object> params) {
		String chatPartnerId = chatDao.getChatPartnerId(params);
		return chatPartnerId;
	}

	@Override
	public void regiChat(ChatDTO chatDto) {
		chatDao.regiChat(chatDto);
	}

	@Override
	public int getChatSpceId(Map<String, Object> params) {
		int chatSpceId = getChatSpceId(params);
		return chatSpceId;
	}

	@Override
	public int getMaxChatSpceId() {
		int maxChatSpceId = chatDao.getMaxChatSpceId();
		return maxChatSpceId;
	}

	@Override
	public String getPartnerId(int bbsId) {
		String partnerId = chatDao.getPartnerId(bbsId);
		return partnerId;
	}

	@Override
	public void createChatRoom(Map<String, Object> params) {
		chatDao.createChatRoom(params);
	}

	@Override
	public int getBbsId(Map<String, Object> params) {
		int bbsId = chatDao.getBbsId(params);
		return bbsId;
	}

	

}
