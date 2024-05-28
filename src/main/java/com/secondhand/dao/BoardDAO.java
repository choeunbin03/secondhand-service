package com.secondhand.dao;

import java.util.List;
import java.util.Map;

import com.secondhand.domain.BoardDTO;

public interface BoardDAO {
	
	public List<BoardDTO> getBbsList();
	public List<BoardDTO> getBbsList(Map<String, Object> param);
	public BoardDTO getBbsView(Map<String, Object> param);
<<<<<<< Updated upstream


=======
	//판매내역 가져오기
	public List<BoardDTO> getPrchBbsList(String mbrId);
	//구매내역 가져오기
	public List<BoardDTO> getSleBbsList(String mbrId);
	//게시물 검색
	public List<BoardDTO> getBbsListByKeyword(String keyword);		
	//찜목록 조회
    public List<BoardDTO> getBbsListByBMK(String mbrId);
    public List<BoardDTO> getBbsListByRecentViewed(String mbrId);
	
	//게시글 삭제 
	public void deleteBoard(int bbsId);	
	//게시글 작성
	void bbsRegi(BoardDTO board);
>>>>>>> Stashed changes
}