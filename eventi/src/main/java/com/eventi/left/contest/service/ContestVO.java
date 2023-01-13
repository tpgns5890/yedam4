package com.eventi.left.contest.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContestVO {
	private String cNo; 	 //공모전번호
	private String writer;   //작성자
	private String ttl; 	 //제목
	private String cntn; 	 //내용
	private String category; //카테고리
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date dtReg; 	 //등록일
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date dtCls;		 //마감일
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date dtExtns; 	 //마감연장일
	private int pay; 	 //결제금액(등록비+총상금)
	private String save = "N";	 //임시저장여부(Y:임시저장)
	private int inq; 	 //조회수
	private int rprt; 	 //신고수
	private String style; 	 //선호하는태그(최대 3개)
	
	//정렬기준
	private String order = "dt_Reg"; //정렬기준
	
	//페이징 처리관련 
	private int first;
	private int last;
	private int page;
	
	//좋아요
	private int likes; 
	
	//D-day
	private int dDay;
	
	
	public String getcNo() {
		return cNo;
	}

	public void setcNo(String cNo) {
		this.cNo = cNo;
	}

	public int getdDay() {
		return dDay;
	}

	public void setdDay(int dDay) {
		this.dDay = dDay;
	}
	
	
}
