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
	
<<<<<<< Updated upstream
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
=======
	public void init();
	public MemberDTO save(MemberDTO member);
	public MemberDTO findByMbrId(String loginId);
	public List<MemberDTO> findAll();
	public void clearStore();
<<<<<<< Updated upstream
	public List<String> getBMK(String mbrId);
	public void updateBMK(Map<String, Object> param);

=======
	// 회원 탈퇴 (삭제 되면 true 반환)
	public boolean delete(MemberDTO member); 
	//로그인한 사용자의 찜 목록 가져오기
	public List<String> getBMK(String mbrId);
	//최근 방문 글 목록
	public List<String> getRecentViewed(String mbrId);
	//찜 추가 및 삭제
	public void updateBMK(Map<String, Object> param);
	//최근방문글 업데이트
	public void updateRecentView(Map<String, Object> param);
>>>>>>> Stashed changes
}
>>>>>>> Stashed changes
