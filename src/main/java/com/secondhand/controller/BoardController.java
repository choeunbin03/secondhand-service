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
		
<<<<<<< Updated upstream
		model.addAttribute("bbsView", bbsView);
				
		return "/board/bbsView";
	}
=======
		//이용자 id 가져오기
		if(session.getAttribute("loginMember") != null) {
			String id = ((MemberDTO)session.getAttribute("loginMember")).getMbrId();
=======
		//첨부파일 가져오기
		List<AtchFileDTO> files = atchFileService.getFiles(param);
		
		//로그인한 사용자 찜 여부 가져오기
		System.out.println("12312");
		if(session.getAttribute("LoginMember") != null) {
			String id = ((MemberDTO)session.getAttribute("LoginMember")).getMbrId();
>>>>>>> Stashed changes
			isbmk = memberService.isBMK(id,Integer.toString(bbsView.getBbsId()));
			memberService.updateRecentView(id, request.getParameter("bbsId"));
			System.out.println(isbmk);
		}
<<<<<<< Updated upstream
		//첨부파일 가져오기
		List<AtchFileDTO> files = atchFileService.getFiles(param);
		//좋아요 체크 여부 및 좋아요 개수 가져오기
		
		//채팅 개수 가져오기

=======
		System.out.println("12312");
		//찜 개수 가져오기
		
		//채팅 개수 가져오기


>>>>>>> Stashed changes
		model.addAttribute("bbsView", bbsView);
		model.addAttribute("isBMK", isbmk);
		model.addAttribute("files", files);
		model.addAttribute("userinfo", session.getAttribute("loginMember"));
				
		return "/board/bbsView";
	}
	
<<<<<<< Updated upstream
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
=======
	//게시글 등록 페이지로 이동
	@RequestMapping(value = "/bbsRegi", method = RequestMethod.GET)
    public String addBoardForm(Model model) {
        model.addAttribute("board", new BoardDTO());
        return "board/bbsRegi";
    }

	//게시글 등록 메소드
    @RequestMapping(value = "/bbsRegi", method = RequestMethod.POST)
    public String bbsRegi(@ModelAttribute("board") BoardDTO boardDto, BindingResult result,
                           @RequestParam(value = "file", required = false) List<MultipartFile> fileList, 
                           RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {
    	
    	MemberDTO member = (MemberDTO) session.getAttribute("LoginMember"); // 세션에서 로그인 멤버 가져오기
		String mbrId = member.getMbrId();// 로그인한 멤버 id가져오기
		int atchFileNo = 0;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mbrId", mbrId);
		
    	
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> logger.error("Error: {}", error.getDefaultMessage()));
            return "board/bbsRegi";
        }
        try {
        	boardDto.setRgtrId(mbrId);
        	boardDto.setMdfrId(mbrId);
        	boardDto.setRgtrDt(new Date());
        	boardDto.setMdfrDt(new Date());

            if (fileList != null && !fileList.get(0).isEmpty()) {
            	
            	atchFileNo = atchFileService.getMaxAtchFileNo() + 1;
            	params.put("atchFileNo", atchFileNo);
            	
                List<Map<String, Object>> filesInfo = atchFileService.submitFiles(fileList);
                if (!filesInfo.isEmpty()) {                	
                	
            		params.put("filesInfo", filesInfo);
            		
            		atchFileService.saveInfo(params);
            		
            		int fileNo = Integer.parseInt(filesInfo.get(0).get("atchFileNo").toString());
                    boardDto.setAtchFileNo(fileNo);
                }
            }

            boardService.bbsRegi(boardDto);
        } catch (Exception e) {
            logger.error("Failed to bbsRegi board: ", e);
            return "redirect:/board/bbsRegi";
        }
        return "redirect:/board/bbsList";
    }
    
    //게시글 검색
  	@RequestMapping(value="/search",method=RequestMethod.GET)
  	public String search(@RequestParam("searchKeyword")String keyword,Model model) {
  		List<BoardDTO>searchResults = boardService.searchBbsListByKeyword(keyword);
  		model.addAttribute("bbsList",searchResults);
  		return "board/bbsList";
  	}
    //찜목록 조회
  	@RequestMapping(value="/bmk",method=RequestMethod.GET)
  	public String bmk(HttpSession session, Model model) {
  		List<BoardDTO>searchResults = boardService.searchBbsListByBMK(((MemberDTO)session.getAttribute("LoginMember")).getMbrId());
  		model.addAttribute("bbsList",searchResults);
  		return "board/bbsList";
  	}
    //최근 게시물 조회
  	@RequestMapping(value="/recentViewed",method=RequestMethod.GET)
  	public String recentViewed(HttpSession session, Model model) {
  		List<BoardDTO>searchResults = boardService.searchBbsListByRecentViewed(((MemberDTO)session.getAttribute("LoginMember")).getMbrId());
  		model.addAttribute("bbsList",searchResults);
  		return "board/bbsList";
  	}
  	
  	//게시글 삭제
  	 @RequestMapping(value="/delete", method=RequestMethod.GET)
  	    public String deleteBoard(@RequestParam("bbsId") int bbsId, RedirectAttributes redirectAttributes) {
  	        boardService.deleteBoard(bbsId);
  	        redirectAttributes.addFlashAttribute("msg", "게시글 삭제 성공");
  	        return "redirect:/board/bbsList";
  	    }
  	 
  	//찜 목록에 추가/삭제
  	@PostMapping("/addBmk")
  	public String addBMK(Locale locale, Model model, HttpSession session, HttpServletRequest request) {
  		String id = ((MemberDTO)session.getAttribute("LoginMember")).getMbrId();	
  		System.out.println("====0=====");
  		System.out.println(request.getParameter("bbsId"));
  		System.out.println(id);
  		System.out.println("====0=====");
  		if (request.getParameter("bmk") != null) {		
  	  		System.out.println("updateBMK...");		
			memberService.updateBMK(id, request.getParameter("bbsId"));
>>>>>>> Stashed changes
		}
		//찜하기/찜해제
    	return "redirect:/board/bbsView?bbsId="+request.getParameter("bbsId");
    }
    
	

>>>>>>> Stashed changes
}
