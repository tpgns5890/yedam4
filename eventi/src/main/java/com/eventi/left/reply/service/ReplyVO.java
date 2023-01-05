package com.eventi.left.reply.service;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ReplyVO { 
	String replyNo;
	String userId;
	String replyTgt;
	String replyCntn;
	String writingDt;
	String boardCat;
	String rerepTgt;
}