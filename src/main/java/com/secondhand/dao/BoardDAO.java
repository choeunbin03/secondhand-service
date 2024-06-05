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
	//게시물 검색
	public List<BoardDTO> getBbsListByKeyword(String keyword);		
	//게시글 삭제 
	public void deleteBoard(int bbsId);	
	//게시글 작성
	public void bbsRegi(BoardDTO board);
	
	//게시글 정보 불러오기
	public BoardDTO findById(int bbsId);
	//게시글 정보 수정
	public void updateBoard(BoardDTO board);
}
