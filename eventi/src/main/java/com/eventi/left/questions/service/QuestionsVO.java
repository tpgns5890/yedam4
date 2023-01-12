package com.eventi.left.questions.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsVO { 
	private String qNo;			//문의번호
	private String userId;		//작성자Id
	private String targetId;	//대상자ID or 게시글유형
	private String Ttl;		//문의제목
	private String Cntn;		//문의내용
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date writingDt;		//작성일자
	private String secretYn;	//비밀글 유무
	private String category;	//대상구분
	private String rerepTgt;	//문의:null 답변:문의번호입력
	
	//날짜 String 변경
	private String dtStr;	
	
	public String getqNo() {
		return qNo;
	}
	public void setqNo(String qNo) {
		this.qNo = qNo;
	}
	
	
}