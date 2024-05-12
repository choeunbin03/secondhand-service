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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.services.datapipeline.model.Field;
import com.secondhand.domain.BoardDTO;
<<<<<<< Updated upstream
=======
import com.secondhand.domain.MemberDTO;
import com.secondhand.service.AtchFileService;
>>>>>>> Stashed changes
import com.secondhand.service.BoardService;
import com.secondhand.service.MemberService;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	
	@Inject
	private BoardService boardService;
<<<<<<< Updated upstream
=======
	@Inject
	private AtchFileService atchFileService;
	@Inject
	private MemberService memberService;
>>>>>>> Stashed changes
	
	//혹시라도 콘솔 창에서 값 출력해보고 싶으면 사용하셔도 됩니다~!
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//게시글 리스트 불러오기.(홈 화면)
	@RequestMapping(value = "/bbsList", method = RequestMethod.GET)
	public String bbsList(Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		//게시글은 사용자의 동네에서 작성된 글이 아니더라도 볼 수 있음.
		List bbsList = new ArrayList<>();
		try {
			//혹시 카테고리를 선택한 경우.
			int ctgryFld = Integer.parseInt((String)request.getParameter("ctgryFld"));
			Map<String, Object> param = new HashMap<>();
			param.put("ctgryFld", ctgryFld);
			
			//카테고리를 선택한 경우 게시글 리스트 가져오기
			bbsList = boardService.getBbsList(param);
			
			//카테고리 필드 값 model에 담기
			model.addAttribute("ctgryFld", ctgryFld);
			//카테고리 값 list를 어디다 저장해야될지 모르겠음
		}catch(Exception e) {
			//카테고리를 선택하지 않은 경우 게시글 리스트 가져오기
			bbsList = boardService.getBbsList();
		}		
				
		model.addAttribute("bbsList", bbsList);
		return "/board/bbsList";
	}
	//게시글 상세페이지 불러오기.
	@RequestMapping(value = "/bbsView", method = RequestMethod.GET)
	public String bbs(Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		Map<String, Object> param = new HashMap<>();
		boolean isbmk = false;
		param.put("bbsId", request.getParameter("bbsId"));
		BoardDTO bbsView = boardService.getBbsView(param);
<<<<<<< Updated upstream
		System.out.println("========1111==========");
		System.out.println(bbsView);
		System.out.println("========1111==========");
		
		model.addAttribute("bbsView", bbsView);
				
		return "/board/bbsView";
	}
=======
		//이용자 id 가져오기
		if(session.getAttribute("loginMember") != null) {
			String id = ((MemberDTO)session.getAttribute("loginMember")).getMbrId();
			isbmk = memberService.isBMK(id,Integer.toString(bbsView.getBbsId()));
		}
		//첨부파일 가져오기
		List<AtchFileDTO> files = atchFileService.getFiles(param);
		//좋아요 체크 여부 및 좋아요 개수 가져오기
		
		//채팅 개수 가져오기

		model.addAttribute("bbsView", bbsView);
		model.addAttribute("isBMK", isbmk);
		model.addAttribute("files", files);
		model.addAttribute("userinfo", session.getAttribute("loginMember"));
				
		return "/board/bbsView";
	}
	
	//3. 찜목록에 추가/삭제.
	@RequestMapping(value = "/bbsView", method = RequestMethod.POST)
    public String addBMK(Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		if(session.getAttribute("loginMember") != null) {
			if (request.getParameter("bmk") != null) {
				String id = ((MemberDTO)session.getAttribute("loginMember")).getMbrId();
				System.out.println("========1111==========");
				System.out.println(request.getParameter("bbsId"));
				System.out.println(id);
				System.out.println("========1111==========");
				memberService.updateBMK(id, request.getParameter("bbsId"));
			}
		}
		//찜하기/찜해제
    	return "redirect:/board/bbsView?bbsId="+request.getParameter("bbsId");
    }
    
	

>>>>>>> Stashed changes
}
