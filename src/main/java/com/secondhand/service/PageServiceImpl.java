package com.secondhand.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.secondhand.domain.PageDTO;

@Service
public class PageServiceImpl implements PageService{
	
	@Override
	public List<PageDTO> makePages(List<Object> list){
	
		int pagesNumber; // 총 페이지 수
		boolean isModeZero=false; // list.size()%listNumberPerPage==0 이면 true;
		if(list.size()%listNumberPerPage==0) isModeZero=true;
		
		if(isModeZero) pagesNumber=list.size()/listNumberPerPage; // 총 페이지 수
		else pagesNumber=list.size()/listNumberPerPage +1;

		// 리턴할 페이지들 만들기
		List<PageDTO> pages=new ArrayList<>(); // 리턴할 페이지들
		if(isModeZero == true) for(int i=0;i<pagesNumber;i++) pages.add(i,new PageDTO(list.subList(i*listNumberPerPage,(i+1)*listNumberPerPage),i));
		else {
			for(int i=0;i<pagesNumber-1;i++) pages.add(i,new PageDTO(list.subList(i*listNumberPerPage,(i+1)*listNumberPerPage),i));
			if(isModeZero == false) pages.add(pagesNumber-1,new PageDTO(list.subList((pagesNumber-1)*listNumberPerPage, list.size()),pagesNumber-1));
		}

		return pages;
	}

	@Override
	public PageDTO getPage(List<PageDTO> pages,int pageNumber) { return pages.get(pageNumber); }
}
