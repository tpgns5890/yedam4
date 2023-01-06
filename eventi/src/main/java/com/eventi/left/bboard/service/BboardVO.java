package com.eventi.left.bboard.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BboardVO {
	private String bBoardNo; //게시글번호
	private String writer; //회원ID
	private String ttl; //제목
	private String lclsf; //대분류
	private String mclsf; //중분류
	private String cntn; //게시글 내용
	private Integer inq; //조회수
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date dt; //작성일자
	private String img; //이미지
	private Integer rprt; //신고건수
	private String type; //게시글유형
}
