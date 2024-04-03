package com.secondhand.service;

import java.util.List;

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

}
