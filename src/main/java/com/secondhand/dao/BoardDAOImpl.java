package com.secondhand.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.secondhand.domain.BoardDTO;

@Repository
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
	public BoardDTO getBbsView(Map<String, Object> param) {
		BoardDTO bbsContent = (BoardDTO) sqlSession.selectList(namespace + ".getBbsView", param).get(0);
		return bbsContent;
	}
}

