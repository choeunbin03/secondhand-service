package com.secondhand.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.secondhand.domain.BoardDTO;
import com.secondhand.domain.MemberDTO;
import com.secondhand.service.BoardServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
@Slf4j
public class MypageController{
	
	private final BoardServiceImpl boardService;

	@GetMapping("/mainPage")
	public String myPage(Model model) {
		log.info("마이페이지로 이동");
		return "/mypage/mainPage";
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
}