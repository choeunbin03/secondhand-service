package com.secondhand.service;

import java.util.List;
import java.util.Map;

import com.secondhand.domain.BoardDTO;

public interface BoardService {
	
	public List<BoardDTO> getBbsList();
	public List<BoardDTO> getBbsList(Map<String, Object> param);
	public List<BoardDTO> getPrchBbsList(String mbrId);
	public List<BoardDTO> getSleBbsList(String mbrId);

}
