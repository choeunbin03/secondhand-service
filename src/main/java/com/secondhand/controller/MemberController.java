package com.secondhand.controller;

import javax.annotation.PostConstruct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.secondhand.dao.MemberDAOImpl;
import com.secondhand.domain.LoginDTO;
import com.secondhand.domain.MemberDTO;
import com.secondhand.service.LoginServiceImpl;

import com.secondhand.dao.MemberDAO;
import com.secondhand.domain.LoginDTO;
import com.secondhand.domain.MemberDTO;
import com.secondhand.service.LoginService;
import com.secondhand.service.MemberService;

import java.util.Set;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController{

    private final LoginServiceImpl loginService; // 멤버 조회역할'
    private final MemberService memberService;


    @GetMapping("/login") // 로그인화면
    public String loginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "/member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDTO") LoginDTO login,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        @RequestParam(name = "redirectURL", defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        loginService.validate(login, bindingResult); //검증기에 주입
        if(bindingResult.hasErrors()){ // 입력 값 없으면 다시 로그인창
            log.info("error1 ={}", bindingResult.getFieldError());
            return "/member/login";
        }
        //LoginService에서 받은 데이터로 로그인 가능한지 처리해야함
        MemberDTO loginMember = loginService.login(login.getLoginId(), login.getPassword()); // 넘겨준 아이디에 맞는 MemberDTO객체 있으면 반환받음

       if(loginMember == null) { // 없는 회원일때
    	   log.info("Login failed for user ID: {}", login.getLoginId());
     	   redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
     	   return "redirect:/member/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("LoginMember", loginMember);
        log.info("OO");
        return "redirect:" + redirectURL;

    }

    @GetMapping("/signin") // 회원가입 화면
    public String signInForm(@ModelAttribute("member") MemberDTO member){
        return "/member/signin";
    }

    @PostMapping("/signin") // 회원가입 화면에서 가입하기 눌렸을 때
    public String singIn(@ModelAttribute("member") MemberDTO member,
                         @ModelAttribute("mbrPwdConfirm") String mbrPwdConfirm,
                         BindingResult bindingResult,
                         @RequestParam(name="redirectURL",defaultValue="/member/login") String redirectURL,
                         HttpServletRequest request,
                         Model model){


        memberService.validate(member, bindingResult);

        Set<String> errorMsgSet = memberService.isValidate(member,mbrPwdConfirm);
        if(errorMsgSet.contains("noError")){ // 회원 가입이 적절할 떄
        	memberService.save(member);
        }
        else { // 회원 가입이 적절하지 않을 때
            log.info("회원 가입 실패");
            if(errorMsgSet .contains("mbrIdError")){ log.info("사용자 이름 제약 조건 불충족");}
            if(errorMsgSet .contains("mbrPwdError")){ log.info("사용자 비밀번호 제약 조건 불충족");}
            if(errorMsgSet .contains("mbrPwdConfirmError")){ log.info("사용자 비밀번호 확인 제약 조건 불충족");}

            return "/member/signin";
        }

        return "redirect:"+redirectURL;
    }
    
    //logout 관련 메소드
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
    	
    	HttpSession session=request.getSession();
    	session.invalidate();
    	
    	return "redirect:/board/bbsList";
    }
    
    // 회원 삭제 관련 메소드
    @GetMapping("/deleteMember")
    public String deleteMember(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "/member/deleteMember";
    }
    
    @PostMapping("/deleteMember")
    public String deleteMember(HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	MemberDTO deleteMember=(MemberDTO)session.getAttribute("loginMember");
    	
    	memberService.delete(deleteMember);
    	session.invalidate();

    	return "redirect:/board/bbsList";
    }
 
    //회원가입 시 동네설정
    @GetMapping("/jusoPopup") 
    public String jusoPopupGet(){
        return "/member/jusoPopup";
    }
    
    @PostMapping("/jusoPopup")
	public String jusoPopupPost() throws Exception {
		return "/member/jusoPopup";
	}
}
