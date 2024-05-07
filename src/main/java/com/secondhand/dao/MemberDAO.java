package com.secondhand.dao;

import java.util.*;

import com.secondhand.domain.MemberDTO;


public interface MemberDAO { //리포지터리

	public MemberDTO save(MemberDTO member);
	public MemberDTO findByMbrId(String LoginId);
//	public List<MemberDTO> findAll();
//	public void clearStore();

}
