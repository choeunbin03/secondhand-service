package com.secondhand.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO extends BaseDTO{
	public int bbsId; //게시글 id
	private String bbsTtl; //게시글 제목
	private String bbsCn; //게시글 내용
	private int ctgryFld; //카테고리 종류
	private int sleCmptnYn; //판매 완료 여부
	private String sleId; //구매자 id
	private int atchFileNo; //첨부파일 번호
	private int bmkCnt; //찜 개수
	private int slePrc; // 판매 가격
}
