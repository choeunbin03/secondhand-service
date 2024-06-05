package com.secondhand.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.secondhand.domain.MemberDTO;
import com.secondhand.service.BoardServiceImpl;
import com.secondhand.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    private final BoardServiceImpl boardService;
    private final MemberService memberService; // MemberService 주입

    @GetMapping("/mainPage")
    public String getProfilePage(HttpSession session, Model model) {
        MemberDTO member = (MemberDTO) session.getAttribute("LoginMember");
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
        List prchBbsList = new ArrayList<>(); // 구매목록
        MemberDTO member = (MemberDTO) session.getAttribute("LoginMember"); // 세션에서 로그인 멤버 가져오기
        String mbrId = member.getMbrId(); // 로그인한 멤버 id 가져오기

        sleBbsList = boardService.getSleBbsList(mbrId); // 멤버 id에 해당되는 구매목록 가져옴
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
        MemberDTO member = (MemberDTO) session.getAttribute("LoginMember");
        if (member == null || member.getMbrId() == null) {
            return "redirect:/login";
        }

        member.setStoreDescription(storeDescription);

        try {
            memberService.updateProfile(member, profilePhoto);
            session.setAttribute("LoginMember", member);
        } catch (Exception e) {
            log.error("Error updating profile", e);
            return "redirect:/myPage/mainPage?error=true";
        }
        return "redirect:/myPage/mainPage";
    }
    @GetMapping("/myReviews")
    public String reviews(HttpServletRequest request, Model model) {
       HttpSession session = request.getSession();
       MemberDTO member = (MemberDTO) session.getAttribute("LoginMember");
       List sleReviewList = new ArrayList<>(); // 나에 대한 후기
       List prchReviewList = new ArrayList<>();// 내가 쓴 후기
       sleReviewList = boardService.getSleBbsList(member.getMbrId());
       prchReviewList = boardService.getPrchBbsList(member.getMbrId());
       model.addAttribute("sleReviewList",sleReviewList);
       model.addAttribute("prchReviewList",prchReviewList);
       log.info("후기 목록 이동");
       return "/mypage/myReviews";   
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
    
}
