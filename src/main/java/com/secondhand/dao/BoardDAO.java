package com.secondhand.dao;

import java.util.List;
import java.util.Map;

import com.secondhand.domain.BoardDTO;

public interface BoardDAO {
	
	public List<BoardDTO> getBbsList();
	public List<BoardDTO> getBbsList(Map<String, Object> param);
	public BoardDTO getBbsView(Map<String, Object> param);


}