package com.eventi.left.contest.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ContestVO {
	private String cNo; 	 //공모전번호
	private String writer;   //작성자
	private String ttl; 	 //제목
	private String cntn; 	 //내용
	private String category; //카테고리
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date dtReg; 	 //등록일
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date dtCls;		 //마감일
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date dtExtns; 	 //마감연장일
	private Integer pay; 	 //결제금액(등록비+총상금)
	private String save;	 //임시저장여부
	private String inq; 	 //조회수
	private String rprt; 	 //신고수
	private String style; 	 //선호하는태그(최대 3개)
}