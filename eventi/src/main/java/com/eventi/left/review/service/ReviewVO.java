package com.eventi.left.review.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ReviewVO { 
	private String reviewNo;	//후기번호
	private String userId;		//작성자Id
	private String reviewTgt;	//후기대상(id or 게시글번호)
	private Double star;		//별점
	private String reviewCntn;	//후기내용
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date writingDt;		//작성일자
	private String img;			//이미지
	private String category;	//대상구분
	
	//별 유무
	private Integer fullstar;   //채워진 별 개수
	private Double halfstar;    //반틈채워진 별 유무
	private Integer laststar;    //남은 별 개수
	
	private Integer recount;    //후기 전체 건수
	private Double reavg;       //후기 평균
}