package com.secondhand.dao;

import java.util.*;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.secondhand.domain.MemberDTO;

@Repository
@Slf4j
public class MemberDAO { //리포지터리

    private static Map<String, MemberDTO> store = new HashMap<>();

    //회원가입 파트에서 멤버 저장하는 로직 구현하고 내가 멤버 조회하는 로직 구현

    @PostConstruct
    public void init(){
        save(new MemberDTO("123","123"));
    }
    public MemberDTO save(MemberDTO member) {
        store.put(member.getMbrId(), member);
        return member;
    }

    public MemberDTO findByMbrId(String LoginId){
       return store.get(LoginId);
    }
//    public Optional<MemberDTO> findByLoginId(String LoginId){ // 회원가입할 때 만든 아이디로 멤버 조회
//        // sql 쿼리로 아이디 조회하기 -> 더 알아봐야할듯
//    }

    public List<MemberDTO> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}