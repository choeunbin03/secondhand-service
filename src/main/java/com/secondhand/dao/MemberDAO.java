package com.secondhand.dao;

import java.util.List;
import java.util.Optional;

import com.secondhand.domain.MemberDTO;

public interface MemberDAO {
	
	public void init();
	public MemberDTO save(MemberDTO member);
	public MemberDTO findByMbrId(String LoginId);
	public List<MemberDTO> findAll();
	public void clearStore();

}
