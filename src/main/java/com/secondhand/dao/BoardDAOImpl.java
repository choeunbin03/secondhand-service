package com.secondhand.dao;

import java.util.List;

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

}
