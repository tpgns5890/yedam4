package com.eventi.left.questions.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class QuestionsRepVO { 
	private String rNo;		//답변번호
	private String qNo;		//문의번호
	private String userId;	//작성자Id
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date writingDt;	//작성일자
	private String rCntn;	//답변내용
	
	public String getrNo() {
		return rNo;
	}
	public void setrNo(String rNo) {
		this.rNo = rNo;
	}
	public String getqNo() {
		return qNo;
	}
	public void setqNo(String qNo) {
		this.qNo = qNo;
	}
	public String getrCntn() {
		return rCntn;
	}
	public void setrCntn(String rCntn) {
		this.rCntn = rCntn;
	}
	
	
}