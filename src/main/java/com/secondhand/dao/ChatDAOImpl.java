package com.secondhand.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.secondhand.domain.ChatDTO;
import com.secondhand.domain.ChatRoomDTO;

@Repository
public class ChatDAOImpl implements ChatDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.secondhand.mappers.chat";

/*	@Override
	public List<ChatRoomDTO> getchatRoomList(String mbrId) {
		List<ChatRoomDTO> chatRoomList = sqlSession.selectList(namespace + ".getchatRoomList", mbrId);
		return chatRoomList;
	}*/

	@Override
	public List<ChatDTO> getChat(Map<String, Object> params) {
		List<ChatDTO> chatView = sqlSession.selectList(namespace + ".getChat", params);
		return chatView;
	}
	
	@Override
	public List<ChatRoomDTO> getchatRoomList(Map<String, Object> params) {
		List<ChatRoomDTO> chatRoomList = sqlSession.selectList(namespace + ".getchatRoomList", params);
		return chatRoomList;
	}

	@Override
	public void updateIdntyYn(Map<String, Object> params) {
		sqlSession.update(namespace + ".updateIdntyYn", params);		
	}

	@Override
	public int getMaxChatId() {
		int maxChatId = sqlSession.selectOne(namespace + ".getMaxChatId");
		return maxChatId;
	}
	
	@Override
	public String getChatPartnerId(Map<String, Object> params) {
		String chatPartnerId = sqlSession.selectOne(namespace + ".getChatPartnerId", params);
		return chatPartnerId;
	}

	@Override
	public void regiChat(ChatDTO chatDto) {
		sqlSession.insert(namespace + ".regiChat", chatDto);
	}



}
