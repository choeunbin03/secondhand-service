package com.secondhand.domain;

import java.util.List;

import lombok.Data;

@Data
public class PageDTO {
	private List<Object> list;
	private int pageNumber;

	public PageDTO(List<Object> list,int pageNumber) {
		this.list=list;
		this.pageNumber=pageNumber;
	}
}