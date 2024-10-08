
package com.secondhand.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.secondhand.domain.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemberDAOImpl implements MemberDAO{ //리포지터리
	
	private static Map<String, MemberDTO> store = new HashMap<>();
	
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.secondhand.mappers.member";

	    //회원가입 파트에서 멤버 저장하는 로직 구현하고 내가 멤버 조회하는 로직 구현

	@Override
    public MemberDTO save(MemberDTO member) {
		sqlSession.insert(namespace + ".save", member);
        log.info("회원 가입 성공 = {}",member);
        return member;
    }

	@Override
    public MemberDTO findByMbrId(String loginId){
		MemberDTO member = sqlSession.selectOne(namespace + ".findByMbrId", loginId);
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
	
	// 멤버 수정
	@Override
	public void edit(MemberDTO member) {
		sqlSession.update(namespace+".edit",member);
		log.info("회원 정보 수정");
		return ;
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
		List<String> bmkList = sqlSession.selectList(namespace + ".getBMKByMbrId",loginId);
		if(bmkList.get(0) == null) {
			return bmkList;
		}
		return Arrays.asList(bmkList.get(0).split(" "));
			
		}
    @Override
    public void updateBMK(Map<String, Object> param) {
		sqlSession.selectList(namespace + ".updateBMKByMbrId",param);			
	}
    
    @Override
    public List<String> getALLRecentView(String loginId) {
		List<String> RecentList = sqlSession.selectList(namespace + ".getRecentViewedByMbrId",loginId);
		if(RecentList.get(0) == null) {
			return RecentList;
		}
		return Arrays.asList(RecentList.get(0).split(" "));
			
	}
    
    @Override
	public List<String> getRecentViewed(String loginId) {
		List<String> bbsList = sqlSession.selectList(namespace + ".getRecentViewedByMbrId", loginId);
		if(bbsList.get(0) == null) {
			return bbsList;
		}
		return Arrays.asList(bbsList.get(0).split(" "));

	}
    
    @Override
	public void updateRecentView(Map<String, Object> param) {
		sqlSession.selectList(namespace + ".updateRecentView", param);
		
	}
    
    @Override

    public void updateProfile(MemberDTO member) throws Exception {
        sqlSession.update(namespace + ".updateProfile", member);
    }

    @Override
    public MemberDTO getUserProfile(String mbrId) throws Exception {
        return sqlSession.selectOne(namespace + ".getUserProfile", mbrId);
    }
}
