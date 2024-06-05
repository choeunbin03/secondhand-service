package com.secondhand.dao;

import java.util.List;
import java.util.Map;

import com.secondhand.domain.AtchFileDTO;

public interface AtchFileDAO {

	//현재까지의 첨부파일 번호 max 값 가져오기
	public int getMaxAtchFileNo();
	//첨부파일 번호 당 첨부파일 seq max 값 가져오기(?) (썸네일 가져오기 위함)
	public int getMaxAtchFileSeq(int atchFileNo);
	public void saveInfo(Map<String, Object> fileObj);
	
	//첨부파일 목록 가져오기
	public List<AtchFileDTO> getFiles(Map<String, Object> params);
	//첨부파일 썸네일 목록 가져오기
	public List<AtchFileDTO> getFileThumbNail();
	//첨부파일 썸네일 목록 가져오기(카테고리 선택)
	public List<AtchFileDTO> getFileThumbNail(Map<String, Object> params);
	//검색시 첨부파일 썸네일 목록 가져오기
	public List<AtchFileDTO>getFileThumbNailSrch(String keyword);
	
}