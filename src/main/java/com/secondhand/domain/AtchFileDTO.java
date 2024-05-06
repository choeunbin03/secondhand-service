package com.secondhand.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtchFileDTO extends BaseDTO{

	private int atchFileNo; //첨부파일 번호
	private int atchFileSeq; //첨부파일 순차번호
	private String atchFilePath; //첨부파일 경로
	private String atchFileNm; //첨부파일명
	private String atchFileOrgNm; //첨부파일 원본명
	private int atchFileSz; //첨부파일 크기
	
}
