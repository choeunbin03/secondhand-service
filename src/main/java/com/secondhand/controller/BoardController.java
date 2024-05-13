package com.secondhand.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.secondhand.domain.AtchFileDTO;
import com.secondhand.domain.BoardDTO;
import com.secondhand.domain.MemberDTO;
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
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
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
		
		//좋아요 체크 여부 및 좋아요 개수 가져오기
		
		//채팅 개수 가져오기
		
		model.addAttribute("bbsView", bbsView);
				
		return "/board/bbsView";
	}
	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	    public String addBoardForm(Model model) {
	        model.addAttribute("board", new BoardDTO());
	        return "board/addBoard";
	    }
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	 public String addBoard(@ModelAttribute("board") BoardDTO board, BindingResult result,
	                        @RequestParam(value = "photo", required = false) MultipartFile photo, 
	                        RedirectAttributes redirectAttributes, HttpServletRequest request) {
	     if (result.hasErrors()) {
	         result.getAllErrors().forEach(error -> logger.error("Error: {}", error.getDefaultMessage()));
	         return "board/addBoard";
	     }

	     HttpSession session = request.getSession(false);
	     if (session == null) {
	         logger.error("Session not found, user is not logged in.");
	         return "redirect:/member/login";
	     }

	     MemberDTO user = (MemberDTO) session.getAttribute("LoginMember");
	     if (user == null) {
	         logger.error("No user found in session, redirecting to login.");
	         return "redirect:/member/login";
	     }

	     String userId = user.getMbrId();
	     board.setRgtrId(userId);
	     board.setMdfrId(userId);
	     board.setRgtrDt(new Date());
	     board.setMdfrDt(new Date());
	     board.setSleId("0");

	     try {
	         if (photo != null && !photo.isEmpty()) {
	             List<MultipartFile> multipartFiles = Arrays.asList(photo);
	             List<Map<String, Object>> fileInfos = atchFileService.submitFiles(multipartFiles);
	             if (!fileInfos.isEmpty()) {
	                 int fileNo = Integer.parseInt(fileInfos.get(0).get("fileNo").toString());
	                 board.setAtchFileNo(fileNo);
	             }
	         }

	         boardService.addBoard(board);
	     } catch (Exception e) {
	         logger.error("Failed to add board: ", e);
	         return "redirect:/board/add";
	     }

	     return "redirect:/board/bbsList";
	 }
	//게시글 검색
	@RequestMapping(value="/board/search",method=RequestMethod.GET)
	public String search(@RequestParam("searchKeyword")String keyword,Model model) {
		List<BoardDTO>searchResults = boardService.searchBbsListByKeyword(keyword);
		model.addAttribute("bbsList",searchResults);
		return "board/bbsList";
	}
	
	//게시글 삭제
	 @RequestMapping(value="/board/delete", method=RequestMethod.GET)
	    public String deleteBoard(@RequestParam("bbsId") int bbsId, RedirectAttributes redirectAttributes) {
	        boardService.deleteBoard(bbsId);
	        redirectAttributes.addFlashAttribute("msg", "게시글 삭제 성공");
	        return "redirect:/board/bbsList";
	    }

}
