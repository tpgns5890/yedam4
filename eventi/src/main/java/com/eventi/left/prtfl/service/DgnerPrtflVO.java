package com.eventi.left.prtfl.service;

import java.util.Date;
import java.util.List;

import com.eventi.left.files.service.FilesVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DgnerPrtflVO {
	private String userId;        //회원ID
	private String userName;      //디자이너이름
	private String oneIntro;      //한줄소개
	private Integer carrYear;     //경력
	private Integer workPrice;    //작업비용
	private Integer workDt;       //작업기간
	private String style;         //작업물분위기
	private String career;        //경력사항
	private Integer inq;          //조회수
	private Date dt;              //등록시간
	private String img;           //대표이미지
	
	private String cnt;           //좋아요수
	
	private List<FilesVO> files;  //파일리스트
	
	Integer first = 1;            //첫페이지
	Integer last = 3;             //마지막페이지
}
