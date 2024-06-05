package com.secondhand.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDTO {
	private String rgtrId; // 작성자
	private Date rgtrDt; // 작성 날짜
	private String mdfrId; // 수정자
	private Date mdfrDt; // 수정 날짜
	
}
