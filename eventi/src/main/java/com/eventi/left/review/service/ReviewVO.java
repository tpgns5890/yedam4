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
	private Integer star;		//별점
	private String reviewCntn;	//후기내용
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date writingDt;		//작성일자
	private String img;			//이미지
	private String category;	//대상구분
}