package com.secondhand.service;

import java.util.List;
import java.util.Map;

import com.secondhand.domain.BoardDTO;

public interface BoardService {
	
	public List<BoardDTO> getBbsList();
	public List<BoardDTO> getBbsList(Map<String, Object> param);
	public BoardDTO getBbsView(Map<String, Object> param);
<<<<<<< Updated upstream
=======
	//사용자의 판매내역 가져오기
	public List<BoardDTO> getPrchBbsList(String mbrId);
	//사용자의 구매내역 가져오기
	public List<BoardDTO> getSleBbsList(String mbrId);
	//게시물 추가기능
	public void bbsRegi(BoardDTO board);
	//게시글 검색기능
	public List<BoardDTO>searchBbsListByKeyword(String keyword);	
	//찜목록 조회기능
	public List<BoardDTO>searchBbsListByBMK(String mbrId);		
	//찜목록 조회기능
	public List<BoardDTO>searchBbsListByRecentViewed(String mbrId);		
	//게시글 삭제기능
	public void deleteBoard(int bbsId);
}
>>>>>>> Stashed changes

}
