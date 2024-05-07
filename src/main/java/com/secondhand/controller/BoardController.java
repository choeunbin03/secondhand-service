package com.secondhand.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secondhand.domain.AtchFileDTO;
import com.secondhand.domain.BoardDTO;
import com.secondhand.service.AtchFileService;
import com.secondhand.service.BoardService;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	
	@Inject
	private BoardService boardService;
	@Inject
	private AtchFileService atchFileService;
	
	//혹시라도 콘솔 창에서 값 출력해보고 싶으면 사용하셔도 됩니다~!
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//1. 게시글 리스트 불러오기.(홈 화면)(default 및 카테고리 보기)
	@RequestMapping(value = "/bbsList", method = RequestMethod.GET)
	public String bbsList(Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		//게시글은 사용자의 동네에서 작성된 글이 아니더라도 볼 수 있음.
		List<BoardDTO> bbsList = new ArrayList<>();
		List<AtchFileDTO> fileList = new ArrayList<>();
		
		try {
			//카테고리를 선택한 경우.
			int ctgryFld = Integer.parseInt((String)request.getParameter("ctgryFld"));
			Map<String, Object> param = new HashMap<>();
			param.put("ctgryFld", ctgryFld);
			
			//카테고리를 선택한 경우 게시글 리스트 가져오기
			bbsList = boardService.getBbsList(param);
			
			//카테고리 선택한 경우 첨부파일 목록 가져오기.
			fileList = atchFileService.getFileThumbNail(param);
			
			//카테고리 필드 값 model에 담기
			model.addAttribute("ctgryFld", ctgryFld);		
			
		}catch(Exception e) {
			//카테고리를 선택하지 않은 경우 게시글 리스트 가져오기
			bbsList = boardService.getBbsList();
			
			//첨부파일 목록 가져오기.
			fileList = atchFileService.getFileThumbNail();
		}		
	
		
		model.addAttribute("bbsList", bbsList);
		model.addAttribute("fileList", fileList);
		return "/board/bbsList";
	}
	
	//2. 게시글 상세페이지 불러오기.
	@RequestMapping(value = "/bbsView", method = RequestMethod.GET)
	public String bbs(Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		Map<String, Object> param = new HashMap<>();
		param.put("bbsId", request.getParameter("bbsId"));
		//게시글 정보 가져오기
		BoardDTO bbsView = boardService.getBbsView(param);
		System.out.println("========1111==========");
		System.out.println(bbsView);
		System.out.println("========1111==========");
		
		//첨부파일 가져오기
		List<AtchFileDTO> files = atchFileService.getFiles(param);
		//좋아요 체크 여부 및 좋아요 개수 가져오기
		
		//채팅 개수 가져오기
		
		model.addAttribute("bbsView", bbsView);
		model.addAttribute("files", files);
				
		return "/board/bbsView";
	}

}
