package com.eventi.left.contest.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.eventi.left.files.service.FilesVO;

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
//	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
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
	
	//정렬,검색
	private String order = "inq DESC"; //기본 정렬기준
	private String search;
	
	//진행,마감체크
	private String ing; 
	
	//우승자등록 체크
	private String winnerCheck;
	
	//공모전 파일
	private String img;
	
	//페이징 처리관련 
	private int first;
	private int last;
	
	//좋아요
	private int likes; //공모전 1건 좋아요개수
	private int userLike; //로그인회원의 좋아요개수
	private String userId; //로그인회원
	
	//D-day
	private int dDay;
	
	
	private int wPay;
	private String categoryCode;
	
	
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
