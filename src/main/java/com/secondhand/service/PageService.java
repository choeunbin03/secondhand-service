package com.secondhand.service;

import java.util.List;

import com.secondhand.domain.PageDTO;

// 어떤 List를 페이징하기 위해서는 PageService의 makePages를 하기 전에, 업 캐스팅 필요 (예시는 BoardController에 '업 캐스팅 예시'로 있음)
// 이후 getPage로 하나의 페이지를 가져오고, 뷰에 넘기기 전에 다운 캐스팅 필요 (예시는 BoardController에 '다운 캐스팅 예시'로 있음)
public interface PageService {
	
	final int listNumberPerPage=3; // 한 페이지당 list 수
	
	// 파라미터의 list를 페이지들로 만든다.
	List<PageDTO> makePages(List<Object> list);
	
	// 하나의 페이지를 리턴한다.
	PageDTO getPage(List<PageDTO> page,int pageNumber);
}