package com.secondhand.service;

import java.util.List;
import java.util.Map;

import com.secondhand.domain.BoardDTO;

public interface BoardService {
	
	//게시글 리스트 가져오기(기본)
	public List<BoardDTO> getBbsList();
	//카테고리 적용된 게시글 리스트 가져오기
	public List<BoardDTO> getBbsList(Map<String, Object> param);

	public List<BoardDTO> getPrchBbsList(String mbrId);
	public List<BoardDTO> getSleBbsList(String mbrId);

	//게시글 상세정보 가져오기
	public BoardDTO getBbsView(Map<String, Object> param);
	
	//게시물 추가기능
	public void addBoard(BoardDTO board);
	
	//게시글 검색기능
	public List<BoardDTO>searchBbsListByKeyword(String keyword);
	
	//게시글 삭제기능
	public void deleteBoard(int bbsId);
	
	
	


}

