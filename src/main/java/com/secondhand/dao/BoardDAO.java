package com.secondhand.dao;

import java.util.List;
import java.util.Map;

import com.secondhand.domain.BoardDTO;

public interface BoardDAO {
	
	//게시글 리스트 가져오기(기본)
	public List<BoardDTO> getBbsList();
	//카테고리 적용된 게시글 리스트 가져오기
	public List<BoardDTO> getBbsList(Map<String, Object> param);
	//게시글 상세정보 가져오기
	public BoardDTO getBbsView(Map<String, Object> param);
	//판매내역 가져오기
	public List<BoardDTO> getPrchBbsList(String mbrId);
	//구매내역 가져오기
	public List<BoardDTO> getSleBbsList(String mbrId);
	//게시물 검색
	public List<BoardDTO> getBbsListByKeyword(String keyword);		
	//게시글 삭제 
	public void deleteBoard(int bbsId);	
	//게시글 작성
	void bbsRegi(BoardDTO board);
}