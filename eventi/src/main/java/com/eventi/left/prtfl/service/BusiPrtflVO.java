package com.eventi.left.prtfl.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusiPrtflVO {
	private String userId;    //업체ID
	private String busiTitle; //업체이름
	private String intro;     //업체소개
	private Integer tel;      //전화번호
	private Date dt;          //등록시간
	private String img;       //대표이미지
	private String busiArea;  //행사가능지역
	private String busiStyle; //행사 진행 스타일
	
	Integer first = 1;        //첫페이지
	Integer last = 6;         //마지막페이지
}
