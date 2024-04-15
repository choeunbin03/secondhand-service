package com.secondhand.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO { // 회원가입할 때 여기에 로그인, 비밀번호 저장
	
 	private Long id; //멤버 리포지터리 식별 번호
    private String mbrId; //
    private String mbrNm;
    private String mbrPwd;
    private String rgn;
    private int rgnScp;
}