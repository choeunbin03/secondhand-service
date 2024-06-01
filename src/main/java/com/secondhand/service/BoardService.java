package com.secondhand.service;

import java.util.List;
import java.util.Map;

import com.secondhand.domain.BoardDTO;

public interface BoardService {
	
	//1. 게시글 리스트 가져오기(기본)
	public List<BoardDTO> getBbsList();
	//2. 카테고리 적용된 게시글 리스트 가져오기
	public List<BoardDTO> getBbsList(Map<String, Object> param);
	//3. 게시글 상세정보 가져오기
	public BoardDTO getBbsView(Map<String, Object> param); //param: bbsId
	//4. 사용자의 판매내역 가져오기
	public List<BoardDTO> getPrchBbsList(String mbrId);
	//5. 사용자의 구매내역 가져오기
	public List<BoardDTO> getSleBbsList(String mbrId);
	//6. 게시물 추가기능
	public void bbsRegi(BoardDTO board);
	//7. 게시글 검색기능
	public List<BoardDTO>searchBbsListByKeyword(String keyword);	
	//8. 찜목록 조회기능
	public List<BoardDTO>searchBbsListByBMK(String mbrId);		
	//9. 최근 본 게시물 조회기능
	public List<BoardDTO>searchBbsListByRecentViewed(String mbrId);
	//10. 게시글 삭제기능
	public void deleteBoard(int bbsId);
	//11. 후기 작성할 게시글 가져오기
	public BoardDTO getBbsById(int bbsId);
	//12. 작성된 후기 게시글에 등록하기
	public void postReview(BoardDTO reviewBbs);
	//13. 거래완료 메서드
	public void sleCmptn(Map<String, Object> param);
	//14. 거래 취소 메서드
	public void sleCmptnCancel(int bbsId);
}