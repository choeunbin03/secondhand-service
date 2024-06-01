package com.secondhand.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.secondhand.domain.AtchFileDTO;
import com.secondhand.domain.BoardDTO;
import com.secondhand.domain.MemberDTO;
import com.secondhand.service.AtchFileService;
import com.secondhand.service.BoardServiceImpl;
import com.secondhand.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
@Slf4j
public class MypageController{
	
	private final BoardServiceImpl boardService;
	@Inject
	private MemberService memberService;
	@Inject
	private AtchFileService atchFileService;

	@GetMapping("/mainPage")
    public String getProfilePage(HttpSession session, Model model) {
        MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
        if (member != null) {
            try {
                member = memberService.getUserProfile(member.getMbrId());
                model.addAttribute("member", member);
            } catch (Exception e) {
                log.error("Error getting profile for member id: " + member.getMbrId(), e);
                return "redirect:/login";
            }
        }
        return "mypage/mainPage";
    }
	
	@GetMapping("/myStore")
	public String myStore(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		List sleBbsList = new ArrayList<>(); // 판매목록 
		List prchBbsList = new ArrayList<>(); //구매목록
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember"); // 세션에서 로그인 멤버 가져오기
		String mbrId = member.getMbrId();// 로그인한 멤버 id가져오기

		sleBbsList = boardService.getSleBbsList(mbrId); // 멤머 id에 해당되는 구매목록 가져옴
		prchBbsList = boardService.getPrchBbsList(mbrId); // 멤버 id에 해당되는 판매목록 가져옴
		model.addAttribute("sleBbsList", sleBbsList); // 뷰에 판매목록 넘겨줌
		model.addAttribute("prchBbsList", prchBbsList); // 뷰에 구매목록 넘겨줌
		
		log.info("내 상점 관리로 이동");
		return "/mypage/myStore"; // 넘겨준 구매 목록, 판매목록 출력시키기
	}
	
	 @PostMapping("/updateProfile")
	 public String updateProfile(@RequestParam(value = "profilePhoto", required = false) MultipartFile profilePhoto,
                                @RequestParam("storeDescription") String storeDescription,
                                HttpSession session) {
		 MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		 if (member == null || member.getMbrId() == null) {
			 return "redirect:/login";
		 }

		 member.setStoreDescription(storeDescription);

		 try {
			 memberService.updateProfile(member, profilePhoto);
			 session.setAttribute("loginMember", member);
		 } catch (Exception e) {
			 log.error("Error updating profile", e);
			 return "redirect:/myPage/mainPage?error=true";
		 }

		 return "redirect:/myPage/mainPage";
	 }
	 
	 @GetMapping("/profile")
	 public String profile(@RequestParam("userId")String userId, Model model) throws Exception {
		 MemberDTO member = memberService.getUserProfile(userId);
		 model.addAttribute("profile", member);
		 List sleReviewList = new ArrayList<>(); // 나에 대한 후기
		 sleReviewList = boardService.getSleBbsList(member.getMbrId());
		 model.addAttribute("sleReviewList",sleReviewList);
   
		 return "profile/profile";
	 }
	 
	 @GetMapping("/myReviews")
	   public String reviews(HttpServletRequest request, Model model) {
	      HttpSession session = request.getSession();
	      MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
	      List sleReviewList = new ArrayList<>(); // 나에 대한 후기
	      List prchReviewList = new ArrayList<>();// 내가 쓴 후기
	      sleReviewList = boardService.getSleBbsList(member.getMbrId());
	      prchReviewList = boardService.getPrchBbsList(member.getMbrId());
	      model.addAttribute("sleReviewList",sleReviewList);
	      model.addAttribute("prchReviewList",prchReviewList);
	      log.info("후기 목록 이동");
	      return "/mypage/myReviews";   
	   }

	 
	 //후기 작성
	 @GetMapping("/writeReview")
	 public String writeReview(@RequestParam("bbsId") int bbsId, Model model, HttpSession session) {
	     
	     BoardDTO reviewBbs = boardService.getBbsById(bbsId); // 후기 작성할 게시글을 게시글id로 찾아옴
	     log.info("reviewBbs아이디 ={}", reviewBbs.getBbsId());
	     model.addAttribute("reviewBbs",reviewBbs); //후기 작성할 게시글 뷰에 넘겨줌 -> 현재 fdbk,fdbkDT가 비어있는 게시글 객체임

	       
	     Map<String, Object> param = new HashMap<>();
	     param.put("bbsId",bbsId);      
	     //첨부파일 가져오기
	     List<AtchFileDTO> files = atchFileService.getFiles(param);
	     log.info("files ={}", files);
	     model.addAttribute("files", files); 
	     return "/mypage/writeReview"; // 뷰에서 리뷰 작성하게 할 것
	  }
	   
	  // 후기 등록
	  @PostMapping("/writeReview") 
	  public String postReview(@RequestParam("fdbk") String fdbk, @RequestParam("bbsId") int bbsId) {
	     BoardDTO reviewBbs = boardService.getBbsById(bbsId);//id에 맞는 게시물 가져옴
	     reviewBbs.setFdbk(fdbk);
	     log.info("후기 등록 bbsId = {}, 리뷰내용 = {}", reviewBbs.getBbsId(), reviewBbs.getFdbk());
	      
	      boardService.postReview(reviewBbs); // 변경된 게시글 객체 정보 SQL에 저장   
	      return "redirect:/board/bbsList"; // 홈 화면 리다이렉트
	  }
}