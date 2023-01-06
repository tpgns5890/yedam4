package com.eventi.left.questions.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class QuestionsVO { 
	String qNo;			//문의번호
	String userId;		//작성자Id
	String targetId;	//대상자ID or 게시글유형
	String qTtl;		//문의제목
	String qCntn;		//문의내용	
	Date writingDt;		//작성일자
	String replyYn;		//답변 유무
	String secretYn;	//비밀글 유무
	String category;	//대상구분
	
}