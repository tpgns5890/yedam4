package com.eventi.left.review.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ReviewVO { 
	String reviewNo;	//후기번호
	String userId;		//작성자Id
	String reviewTgt;	//후기대상(id or 게시글번호)
	Integer star;		//별점
	String reviewCntn;	//후기내용
	Date writingDt;		//작성일자
	String img;			//이미지
	String category;	//대상구분
}