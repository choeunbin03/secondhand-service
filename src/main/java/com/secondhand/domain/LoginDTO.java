package com.secondhand.domain;

import lombok.Data;

@Data
public class LoginDTO{ //이 클래스의 필드 변수로 입력받은 후 MemberDTO의 로그인 아이디,비밀번호가 맞는지 비교용 클래스
	private String loginId;
	private String password;

	
}