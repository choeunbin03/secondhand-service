package com.secondhand.dao;

import java.util.List;
import java.util.Optional;

import com.secondhand.domain.MemberDTO;

public interface MemberDAO {
	
	public MemberDTO save(MemberDTO member);
	public MemberDTO findByMbrId(String loginId);
	public List<MemberDTO> findAll();
	public void clearStore();
	
	// 회원 탈퇴 (삭제 되면 true 반환)
	public boolean delete(MemberDTO member); 
}
