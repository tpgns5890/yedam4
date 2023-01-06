package com.eventi.left.reply.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ReplyVO { 
	String replyNo;		//댓글번호
	String userId;		//작성자ID
	String replyTgt;	//댓글대상(게시글번호)	
	String replyCntn;	//댓글내용
	String writingDt;	//작성일자
	String boardCat;	//게시글유형(대상유형)
	String rerepTgt;	//대댓글대상(댓글번호. 댓글일시 null)
}