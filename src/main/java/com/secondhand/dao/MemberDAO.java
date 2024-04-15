package com.secondhand.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.secondhand.domain.MemberDTO;

@Repository
public class MemberDAO { //리포지터리
	
	private static Map<Long,MemberDTO> store = new HashMap<>();
	private static final Long sequence = 0L; // 멤버 고유 저장 번호
	
	//회원가입 파트에서 멤버 저장하는 로직 구현하고 내가 멤버 조회하는 로직 구현
	
	public MemberDTO save(MemberDTO member) {
		
		//멤버 저장 로직 구현
		return member;
	}
	
	public MemberDTO findById(String id) { // 고유 아이디로 멤버 조회
		return store.get(id); // 이것도 sql로 ?
	}
	
	public Optional<MemberDTO> findByLoginId(String LoginId){ // 회원가입할 때 만든 아이디로 멤버 조회
		// sql 쿼리로 아이디 조회하기 -> 더 알아봐야할듯
	}
	
	public List<MemberDTO> findAll(){
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
}