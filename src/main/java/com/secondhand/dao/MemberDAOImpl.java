package com.secondhand.dao;


import java.util.*;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.secondhand.domain.MemberDTO;

@Repository
@Slf4j
public class MemberDAOImpl implements MemberDAO { //리포지터리

    private static Map<String, MemberDTO> store = new HashMap<>();

    @Autowired
    private SqlSession sqlSession;	
	private static String namespace = "com.secondhand.mappers.member";


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