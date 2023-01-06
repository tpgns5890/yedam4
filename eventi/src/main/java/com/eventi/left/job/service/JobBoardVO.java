package com.eventi.left.job.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobBoardVO {
	private String jobNo; //구인게시글번호
	private String jobCntn; //구인게시글내용
	Integer pay; //수당
	private String loc; //위치
	private String eType; //행사종류
	private String wSday; //근무시작일
	private String wEday; //근무종료일
	private String wHour; //근무시간
	private String qualification; //자격
	private String preference; //우대사항
	private String img; //이미지
	private String userId; //회원ID
	Integer interest; //관심수
}
