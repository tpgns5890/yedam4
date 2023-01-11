package com.eventi.left.contest.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date dtReg; 	 //등록일
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date dtCls;		 //마감일
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
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
	
	//좋아요
	private Integer likes; 
	
	//남은날짜
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
