package com.secondhand.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.secondhand.dao.BoardDAO;
import com.secondhand.domain.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO boardDao;
	private S3Service s3Service;
	private BoardDTO board;

	@Override
	public List<BoardDTO> getBbsList() {
		List<BoardDTO> bbsList = boardDao.getBbsList();
		return bbsList;
	}

	@Override
	public List<BoardDTO> getBbsList(Map<String, Object> param) {
		List<BoardDTO> bbsList = boardDao.getBbsList(param);
		return bbsList;
	}
	
	@Override
	public BoardDTO getBbsView(Map<String, Object> param) {
		BoardDTO bbsContent = boardDao.getBbsView(param);
		return bbsContent;
	}
	
	@Override
	public List<BoardDTO> getPrchBbsList(String mbrId) {
		List<BoardDTO> bbsList = boardDao.getPrchBbsList(mbrId);
		return bbsList;
	}
	
	@Override
	public List<BoardDTO> getSleBbsList(String mbrId) {
		List<BoardDTO> bbsList = boardDao.getSleBbsList(mbrId);
		return bbsList;
	}
	
	@Override
	public void bbsRegi(BoardDTO board) {
		boardDao.bbsRegi(board);
	}
	
	@Override
	public List<BoardDTO>searchBbsListByKeyword(String keyword){
		return boardDao.getBbsListByKeyword(keyword);
	}
	
	@Override
	public List<BoardDTO>searchBbsListByBMK(String mbrId){
		return boardDao.getBbsListByBMK(mbrId);
	}
	
	@Override
	public List<BoardDTO>searchBbsListByRecentViewed(String mbrId){
		return boardDao.getBbsListByRecentViewed(mbrId);
		
	}
	
	@Override
    public BoardDTO findById(int bbsId) {
        return boardDao.findById(bbsId);
    }

    @Override
    public void updateBoard(BoardDTO board) {
        boardDao.updateBoard(board);
    }
	
	@Override
    public void deleteBoard(int bbsId) {
        boardDao.deleteBoard(bbsId);
    }
	
	@Override
	public BoardDTO getBbsById(int bbsId) {
		return boardDao.getBbsById(bbsId);
	}

	@Override
	public void postReview(BoardDTO reviewBbs) {
		boardDao.postReview(reviewBbs);   
	}

	@Override
	public void sleCmptn(Map<String, Object> param) {
		boardDao.sleCmptn(param);
	}

	@Override
	public void sleCmptnCancel(int bbsId) {
		boardDao.sleCmptnCancel(bbsId);
	}

}
