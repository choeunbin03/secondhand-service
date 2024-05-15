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
    public void deleteBoard(int bbsId) {
        boardDao.deleteBoard(bbsId);
    }


	

}
