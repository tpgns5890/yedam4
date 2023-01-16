package com.eventi.left.promotion.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PromotionVO {
	private String proNo; //홍보게시글번호
	private	String userId; //회원ID
	private String eName; //행사명
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date eSday; //행사시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date eEday; //행사종료일
	private String loc; //위치
	private String intro; //소개
	private String eType; //행사유형
	private String entrance; //입장료유무
	private String accommodate; //수용인원
	private String img; //첨부파일
	private Integer see; //관심수
}
