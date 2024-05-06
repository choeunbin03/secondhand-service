package com.secondhand.service;

import org.springframework.validation.Validator;

import com.secondhand.domain.MemberDTO;

//로그인 및 로그아웃 관련 service
public interface LoginService extends Validator{

	//로그인 메소드
	public MemberDTO login(String loginId, String password);
	
}
