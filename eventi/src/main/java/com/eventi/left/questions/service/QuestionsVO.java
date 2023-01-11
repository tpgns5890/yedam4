package com.eventi.left.questions.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class QuestionsVO { 
	private String qNo;			//문의번호
	private String userId;		//작성자Id
	private String targetId;	//대상자ID or 게시글유형
	private String qTtl;		//문의제목
	private String qCntn;		//문의내용
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date writingDt;		//작성일자
	private String replyYn;		//답변 유무
	private String secretYn;	//비밀글 유무
	private String category;	//대상구분
	
	public String getqNo() {
		return qNo;
	}
	public void setqNo(String qNo) {
		this.qNo = qNo;
	}
	public String getqTtl() {
		return qTtl;
	}
	public void setqTtl(String qTtl) {
		this.qTtl = qTtl;
	}
	public String getqCntn() {
		return qCntn;
	}
	public void setqCntn(String qCntn) {
		this.qCntn = qCntn;
	}
	
	
}