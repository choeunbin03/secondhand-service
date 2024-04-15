package com.secondhand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.secondhand.domain.MemberDTO;
import com.secondhand.service.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController{
	
	private LoginService memberService; // 멤버 조회역할
	
//	@GetMapping("~~~") url 주소 정한 후 작성
	public String loginForm(@ModelAttribute("member") MemberDTO member) {
		return "렌더링할 jsp주소";
	}
	
	@PostMapping("~~~")
	public String login(@ModelAttribute("member") MemberDTO member) {
		//LoginService에서 받은 데이터로 로그인 가능한지 처리해야함
		
		return "redirect:" + redirectURL;
	}
}