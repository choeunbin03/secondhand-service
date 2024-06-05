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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.secondhand.domain.AtchFileDTO;
import com.secondhand.domain.BoardDTO;
import com.secondhand.domain.MemberDTO;
import com.secondhand.domain.PageDTO;
import com.secondhand.service.AtchFileService;
import com.secondhand.service.BoardService;
import com.secondhand.service.MemberService;
import com.secondhand.service.PageService;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	
	@Inject
	private BoardService boardService;
	@Inject
	private AtchFileService atchFileService;
	@Inject
	private MemberService memberService;
	
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
	public String bbsView(Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		
		boolean isbmk = false;
		Map<String, Object> param = new HashMap<>();
		param.put("bbsId", request.getParameter("bbsId"));
		//게시글 정보 가져오기
		BoardDTO bbsView = boardService.getBbsView(param);
		
		//첨부파일 가져오기
		List<AtchFileDTO> files = atchFileService.getFiles(param);
		
		//로그인한 사용자 찜 여부 가져오기
		if(session.getAttribute("loginMember") != null) {
			String id = ((MemberDTO)session.getAttribute("loginMember")).getMbrId();
			isbmk = memberService.isBMK(id,Integer.toString(bbsView.getBbsId()));
			memberService.updateRecentView(id, request.getParameter("bbsId"));
		}
		//찜 개수 가져오기
		
		//채팅 개수 가져오기
		
		model.addAttribute("bbsView", bbsView);
		model.addAttribute("files", files);
		model.addAttribute("isBMK", isbmk);
		model.addAttribute("userinfo", session.getAttribute("loginMember"));
				
		return "/board/bbsView";
	}
	
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
    	
    	MemberDTO member = (MemberDTO) session.getAttribute("loginMember"); // 세션에서 로그인 멤버 가져오기
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
  		String id = ((MemberDTO)session.getAttribute("loginMember")).getMbrId();	
  		if (request.getParameter("bmk") != null) {				
			memberService.updateBMK(id, request.getParameter("bbsId"));
		}
		//찜하기/찜해제
    	return "redirect:/board/bbsView?bbsId="+request.getParameter("bbsId");
    }
  	
  	 //찜목록 조회
  	@RequestMapping(value="/bmk",method=RequestMethod.GET)
  	public String bmk(HttpSession session, Model model) {
		List<AtchFileDTO> fileList = new ArrayList<>();
  		List<BoardDTO>searchResults = boardService.searchBbsListByBMK(((MemberDTO)session.getAttribute("loginMember")).getMbrId());
		Map<String, Object> param = new HashMap<String, Object>();
		String bbsIdList = "("+String.join(",",memberService.getALLBMK(((MemberDTO)session.getAttribute("loginMember")).getMbrId()))+")";
		if(!bbsIdList.equals("()")) {
			param.put("bbsIdList",bbsIdList);
			fileList = atchFileService.getFileThumbNailByIdlist(param);
		}
		model.addAttribute("bbsList",searchResults);
		model.addAttribute("fileList", fileList);
  		return "board/bbsList";
  	}
    //최근 게시물 조회
  	@RequestMapping(value="/recentViewed",method=RequestMethod.GET)
  	public String recentViewed(HttpSession session, Model model) {
		List<AtchFileDTO> fileList = new ArrayList<>();
  		List<BoardDTO>searchResults = boardService.searchBbsListByRecentViewed(((MemberDTO)session.getAttribute("loginMember")).getMbrId());

		Map<String, Object> param = new HashMap<String, Object>();
		String bbsIdList = "("+String.join(",",memberService.getALLRecentView(((MemberDTO)session.getAttribute("loginMember")).getMbrId()))+")";
		if(!bbsIdList.equals("()")) {
			param.put("bbsIdList",bbsIdList);
			fileList = atchFileService.getFileThumbNailByIdlist(param);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		//fileList = atchFileService.getFileThumbNailByIdlist(param);
		model.addAttribute("bbsList",searchResults);
		model.addAttribute("fileList", fileList);
  		return "board/bbsList";
  	}
  	
  	//거래완료 메서드
  	@PostMapping("/sleCmptn")
  	@ResponseBody
  	public void sleCmptn(@RequestParam("bbsId") int bbsId, @RequestParam("chatPartnerId") String chatPartnerId,HttpSession session, HttpServletRequest request) {
  		String mbrId = ((MemberDTO)session.getAttribute("loginMember")).getMbrId();
  		//chat partnerId도 가져와서
  		//bbs rgtrId와 다른 id를 mbrID로 넘기기.
  		
  		Map<String, Object> param = new HashMap<>();
  		param.put("bbsId", bbsId);
  		
  		BoardDTO bbsContent = boardService.getBbsView(param);
  		String rgtrId = bbsContent.getRgtrId();
  		
  		if(rgtrId == mbrId) {
  			param.put("prchId", chatPartnerId);
  		}else {
  			param.put("prchId", mbrId);
  		}
  		
  		boardService.sleCmptn(param);
  	}
  	
  	//거래 취소 메서드
  	@PostMapping("/sleCmptnCancel")
  	@ResponseBody
  	public void sleCmptnCancel(@RequestParam("bbsId") int bbsId ,HttpSession session, HttpServletRequest request) {
  		
  		boardService.sleCmptnCancel(bbsId);
  	}
  	
  	
}
