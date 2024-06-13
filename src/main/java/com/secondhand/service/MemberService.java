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
	//프로필 업데이트
	public void updateProfile(MemberDTO member, MultipartFile profilePhoto) throws Exception;
	//사용자 프로필 가져오기
	public MemberDTO getUserProfile(String mbrId) throws Exception;
	//최근본글 조회
	public List<String> getALLRecentView(String s);
	//최근방문글 업데이트
	public void updateRecentView(String userId, String bbsId);
	// 멤버 찾는 메소드
	public MemberDTO findMemberById(String userId);
	// 멤버 수정 메소드
	public String editMember(MemberDTO beforeEditMember,MemberDTO afterEditMember);

}
