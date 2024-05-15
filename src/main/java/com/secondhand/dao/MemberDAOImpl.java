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
      sqlSession.insert(namespace+".save",member);
        log.info("회원 가입 성공 = {}",member);
        return member;
    }

	@Override
    public MemberDTO findByMbrId(String LoginId){

    	MemberDTO member = sqlSession.selectOne(namespace + ".findByMbrId", LoginId);
    	log.info("멤버정보 = {}", member);
    	return member;
    }

	// 회원 탈퇴 (삭제 되면 true 반환)
	@Override
	public boolean delete(MemberDTO member) {
		if(sqlSession.delete(namespace + ".delete",member)!=1) {
			log.info("회원 탈퇴 실패");
			return false;
		}
		else {
			log.info("회원 탈퇴 성공");
			return true;
		}
	}
	
	@Override
    public List<MemberDTO> findAll(){
        return new ArrayList<>(store.values());
    }

	@Override
    public void clearStore() {
        store.clear();
    }
	
	@Override
    public List<String> getBMK(String loginId) {
		List<String> bbsList = sqlSession.selectList(namespace + ".getBMKByMbrId",loginId);
		System.out.println(bbsList);
		return Arrays.asList(bbsList.get(0).split(" "));
			
		}
    @Override
    public void updateBMK(Map<String, Object> param) {
		sqlSession.selectList(namespace + ".updateBMKByMbrId",param);			
	}
}