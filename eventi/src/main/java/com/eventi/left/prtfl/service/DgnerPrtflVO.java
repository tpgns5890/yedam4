package com.eventi.left.prtfl.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DgnerPrtflVO {
	private String userId; //회원ID
	private String oneIntro; //한줄소개
	private Integer carrYear; //경력
	private Integer workPrice; //작업비용
	private Integer workDt; //작업기간
	private String style; //작업물분위기
	private String career; //경력사항
	private Integer inq; //조회수
	private Date dt; //등록시간
	private String img; //대표이미지
}
