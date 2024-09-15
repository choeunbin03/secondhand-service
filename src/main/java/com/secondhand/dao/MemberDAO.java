package com.secondhand.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.secondhand.domain.MemberDTO;

public interface MemberDAO {
	
	public MemberDTO save(MemberDTO member);
	public MemberDTO findByMbrId(String loginId);
	public List<MemberDTO> findAll();
	public void clearStore();
	// 회원 탈퇴 (삭제 되면 true 반환)
	public boolean delete(MemberDTO member); 
	//로그인한 사용자의 찜 여부 가져오기
	public List<String> getBMK(String mbrId);
	//찜 추가 및 삭제
	public void updateBMK(Map<String, Object> param);
<<<<<<< HEAD

=======
	//로그인한 사용자의 최근본글 목록 여부 가져오기
	public List<String> getALLRecentView(String mbrId);
>>>>>>> 67c7351913d3d7b685702f20c8a57c2edc988ea0
	//최근 방문 글 목록
	public List<String> getRecentViewed(String mbrId);
	//최근방문글 업데이트
	public void updateRecentView(Map<String, Object> param);
	//프로필 사진 변경
	public void updateProfile(MemberDTO member) throws Exception;
	   
<<<<<<< HEAD
    MemberDTO getUserProfile(String mbrId) throws Exception;
    
    // 회원 정보 수정
    public void edit(MemberDTO member);
=======
    public MemberDTO getUserProfile(String mbrId) throws Exception;
>>>>>>> 67c7351913d3d7b685702f20c8a57c2edc988ea0
}


