package com.secondhand.dao;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.secondhand.domain.MemberDTO;

@Repository
@Slf4j
public class MemberDAOImpl implements MemberDAO { //리포지터리

    private static Map<String, MemberDTO> store = new HashMap<>();

    @Autowired
    private SqlSession sqlSession;	
	private static String namespace = "com.secondhand.mappers.member";

    //회원가입 파트에서 멤버 저장하는 로직 구현하고 내가 멤버 조회하는 로직 구현

	@Override
    public MemberDTO save(MemberDTO member) {
        store.put(member.getMbrId(), member);
        return member;
    }

	@Override
    public MemberDTO findByMbrId(String LoginId){
    	MemberDTO member = sqlSession.selectOne(namespace + ".findByMbrId", LoginId);
    	log.info("멤버정보 = {}", member);
    	return member;
    }

	@Override
    public List<MemberDTO> findAll(){
        return new ArrayList<>(store.values());
    }

	@Override
    public void clearStore() {
        store.clear();
    }
}