package com.eventi.left.job.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobBoardVO {
	private String jobNo; //구인게시글번호
	private String title; //제목
	private String jobCntn; //구인게시글내용
	Integer pay; //수당
	private String loc; //위치
	private String eType; //행사종류
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date wSday; //근무시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date wEday; //근무종료일
	private String wHour; //근무시간
	private String qualification; //자격
	private String preference; //우대사항
	private String img; //이미지
	private String userId; //회원ID
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date dt; //작성날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date closeDt;
	
	Integer first =1; //첫 페이지
	Integer last = 4; //마지막 페이지
	//좋아요 수 
	Integer cnt;
	//구직신청한 수 
	Integer reCount;
	//마감 남은 일수
	private Integer remain;
}
