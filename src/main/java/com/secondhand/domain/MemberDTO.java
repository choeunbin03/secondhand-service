package com.secondhand.domain;

import lombok.Data;

@Data
public class MemberDTO { // 회원가입할 때 여기에 로그인, 비밀번호 저


    private String mbrId; //회원 아이디
    private String mbrNm; // 회원 이름
    private String mbrPwd;// 비밀번호
    private String rgn;// 지역
    private int rgnScp;// 지역반경
    private String rgnView;//최근 본 상품
    private String sleList;// 판매목록
    private String prchsList;// 구매목록
    private String srchWrd;//검색어목록
    private String bmk;//찜목록
    private String joinDt;//회원가입날짜

    public MemberDTO() {}
    public MemberDTO(String mbrId, String mbrPwd){
        this.mbrId = mbrId;
        this.mbrPwd = mbrPwd;
    }

}


