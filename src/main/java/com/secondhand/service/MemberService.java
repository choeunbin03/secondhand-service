package com.secondhand.service;

import java.util.List;
import java.util.Set;

import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.secondhand.domain.MemberDTO;

public interface MemberService extends Validator{
	
	//회원가입 시 제약조건 확인 메소드
	public Set<String> isValidate(MemberDTO member, String mbrPwdConfirm);
	//(회원가입)회원 정보 저장 메소드
	public void save(MemberDTO member);
	// 회원 탈퇴 메소드
	public boolean delete(MemberDTO member);
	//찜목록 조회
	public List<String> getALLBMK(String s);
	//찜 되어있는지 조회
	public boolean isBMK(String s, String bbsId);
	//찜 추가 및 삭제
	public void updateBMK(String userId, String bbsId);
	void updateProfile(MemberDTO member, MultipartFile profilePhoto) throws Exception;
	MemberDTO getUserProfile(String mbrId) throws Exception;

}
