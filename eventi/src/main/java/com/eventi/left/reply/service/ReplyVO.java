package com.eventi.left.reply.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ReplyVO { 
	private String replyNo;		//댓글번호
	private String userId;		//작성자ID
	private String replyTgt;	//댓글대상(게시글번호)	
	private String replyCntn;	//댓글내용
	@JsonFormat(pattern = "yyyy/MM/dd") 
	private Date writingDt;	//작성일자
	private String boardCat;	//게시글유형(대상유형)
	private String rerepTgt;	//대댓글대상(댓글번호. 댓글일시 null)
}