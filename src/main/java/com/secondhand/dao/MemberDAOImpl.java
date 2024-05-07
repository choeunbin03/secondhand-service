package com.secondhand.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.secondhand.domain.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemberDAOImpl implements MemberDAO{ //리포지터리
	
	private static Map<String, MemberDTO> store = new HashMap<>();

	    //회원가입 파트에서 멤버 저장하는 로직 구현하고 내가 멤버 조회하는 로직 구현
	@Override
    @PostConstruct
    public void init(){
        save(new MemberDTO("123","123"));
    }
	
	@Override
    public MemberDTO save(MemberDTO member) {
        store.put(member.getMbrId(), member);
        return member;
    }

	@Override
    public MemberDTO findByMbrId(String loginId){
       return store.get(loginId);
    }
//	    public Optional<MemberDTO> findByLoginId(String loginId){ // 회원가입할 때 만든 아이디로 멤버 조회
//	        // sql 쿼리로 아이디 조회하기 -> 더 알아봐야할듯
//	    }

	@Override
    public List<MemberDTO> findAll(){
        return new ArrayList<>(store.values());
    }

	@Override
    public void clearStore() {
        store.clear();
    }
}