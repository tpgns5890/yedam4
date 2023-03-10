package com.eventi.left.bboard.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date dt; //작성일자
	private String ano = "N"; //익명여부
	private int rprt; //신고건수
	private String type; //게시글유형
	private String save = "N"; // 저장여부
	
	private Integer cnt; //게시글 별 좋아요 수
	private String order = "dt"; //조회수, 작성일자 등 정렬에 필요
	private String checkano; //익명일 경우 작성자 ID
	
	Integer first = 1; //첫페이지
	Integer last = 5; //마지막페이지
}
