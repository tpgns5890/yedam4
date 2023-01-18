package com.eventi.left.reply.service;

import java.util.List;


public interface ReplyService {
	
	// 댓글
	public List<ReplyVO> replyList(ReplyVO ReplyVO); // 전체조회

	public ReplyVO getReply(ReplyVO ReplyVO); // 1건조회
	
	//댓글 추가
	public int replyInsert(ReplyVO replyVO);
	
	//댓글 수정
	public int replyUpdate(ReplyVO replyVO);

	// 삭제
	public int replyDelete(ReplyVO ReplyVO);
}
