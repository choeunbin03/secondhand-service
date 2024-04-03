package com.secondhand.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secondhand.service.BoardService;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	
	@Inject
	private BoardService boardService;
	
	//혹시라도 콘솔 창에서 값 출력해보고 싶으면 사용하셔도 됩니다~!
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//게시글 리스트 불러오기.(홈 화면)
	@RequestMapping(value = "/bbsList", method = RequestMethod.GET)
	public String bbsList(Locale locale, Model model, HttpSession session) {
		//게시글은 사용자의 동네에서 작성된 글이 아니더라도 볼 수 있음.
		
		//게시글 리스트 가져오기
		List bbsList = boardService.getBbsList();
				
		//첨부파일 목록 가져오기.
		
		model.addAttribute("bbsList", bbsList);
		return "/board/bbsList";
	}

}
