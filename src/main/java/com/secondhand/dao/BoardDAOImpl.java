package com.secondhand.dao;

import java.util.List; 
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.secondhand.domain.BoardDTO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BoardDAOImpl implements BoardDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.secondhand.mappers.board";
	
	@Override
	public List<BoardDTO> getBbsList() {
		List<BoardDTO> bbsList = sqlSession.selectList(namespace + ".getBbsList");
		return bbsList;
	}

	@Override
	public List<BoardDTO> getBbsList(Map<String, Object> param) {
		List<BoardDTO> bbsList = sqlSession.selectList(namespace + ".getBbsListByCtgry", param);
		return bbsList;
	}
	
	@Override
	public List<BoardDTO> getPrchBbsList(String mbrId){ // 해당 멤버의 구매내역에 해당되는 리스트만 가져옴
		List<BoardDTO> bbsList = sqlSession.selectList(namespace + ".getPrchBbsList", mbrId);
		log.info("구매 내역 = {}", bbsList.get(0));
		return bbsList;
	}
	
	@Override
	public List<BoardDTO> getSleBbsList(String mbrId){ // 해당 멤버의 구매내역에 해당되는 리스트만 가져옴
		List<BoardDTO> bbsList = sqlSession.selectList(namespace + ".getSleBbsList", mbrId);
		log.info("판매 내역 = {}", bbsList.get(0));
		return bbsList;
	}

}
