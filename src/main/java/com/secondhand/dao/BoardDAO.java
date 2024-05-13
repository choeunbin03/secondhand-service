package com.secondhand.dao;

import java.util.List;
import java.util.Map;

import com.secondhand.domain.BoardDTO;

public interface BoardDAO {
	
	//게시글 리스트 가져오기(기본)
	public List<BoardDTO> getBbsList();
	//카테고리 적용된 게시글 리스트 가져오기
	public List<BoardDTO> getBbsList(Map<String, Object> param);

	public List<BoardDTO> getPrchBbsList(String mbrId);
	public List<BoardDTO> getSleBbsList(String mbrId);


	public BoardDTO getBbsView(Map<String, Object> param);
	
	void insertBoard(BoardDTO board);
	
	//게시물 검색
	public List<BoardDTO> getBbsListByKeyword(String keyword);
	
	//게시글 삭제 
	public void deleteBoard(int bbsId);
	
}
