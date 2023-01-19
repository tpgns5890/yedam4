package com.eventi.left.questions.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class QuestionsVO { 
	private String qNo;			//문의번호
	private String userId;		//작성자Id
	private String targetId;	//대상자ID or 게시글유형
	private String Ttl;		//문의제목
	private String Cntn;		//문의내용
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date writingDt;		//작성일자
	private String secretYn = "N";	//비밀글 유무
	private String category;	//대상구분
	private String rerepTgt;	//문의:null 답변:문의번호입력
	
	//답변유무
	String ans;
	
	public String getqNo() {
		return qNo;
	}
	public void setqNo(String qNo) {
		this.qNo = qNo;
	}
	
	
}