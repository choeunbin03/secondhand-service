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

	@Override
	public int getChatSpceId(Map<String, Object> params) {
		int chatSpceId = 0;
		try {//채팅방이 존재하지 않으면 NullPointerException
			chatSpceId = sqlSession.selectOne(namespace + ".getChatSpceId", params);
		}catch(NullPointerException e) {
			//채팅방이 존재하지 않을 때 chatSpceId를 할당해서 새로운 채팅방을 생성하는 쿼리를 여기다가 넣을까? 고민
		}		
		return chatSpceId;
	}

	@Override
	public int getMaxChatSpceId() {
		int maxChatSpceId = sqlSession.selectOne(namespace + ".getMaxChatSpceId");
		return maxChatSpceId;
	}

	@Override
	public String getPartnerId(int bbsId) {
		String partnerId = sqlSession.selectOne(namespace + ".getPartnerId", bbsId);
		return partnerId;
	}

	@Override
	public void createChatRoom(Map<String, Object> params) {
		sqlSession.insert(namespace + ".createChatRoom", params);
	}

	@Override
	public int getBbsId(Map<String, Object> params) {
		int bbsId = sqlSession.selectOne(namespace + ".getBbsId", params);
		return bbsId;
	}

	@Override
	public void deleteBbs(int bbsId) {
		sqlSession.update(namespace + ".deleteBbs", bbsId);
	}



}
