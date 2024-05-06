package com.secondhand.service;

import java.util.Set;

import org.springframework.validation.Validator;

import com.secondhand.domain.MemberDTO;

public interface MemberService extends Validator{
	
	//회원가입 시 제약조건 확인 메소드
	public Set<String> isValidate(MemberDTO member, String mbrPwdConfirm);
	//(회원가입)회원 정보 저장 메소드
	public void save(MemberDTO member);
	
}
