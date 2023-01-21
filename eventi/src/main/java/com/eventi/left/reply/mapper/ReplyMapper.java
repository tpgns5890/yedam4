package com.eventi.left.reply.mapper;

import java.util.List;

import com.eventi.left.reply.service.ReplyVO;

public interface ReplyMapper {
	
	// 댓글
	public List<ReplyVO> replyList(ReplyVO ReplyVO); // 전체조회

	public ReplyVO getReply(ReplyVO ReplyVO); // 1건조회
		
	//댓글 추가
	public int replyInsert(ReplyVO replyVO);
		
	//댓글 수정
	public int replyUpdate(ReplyVO replyVO);
		
	// 삭제
	public int replyDelete(ReplyVO ReplyVO);
	
	//게시글 삭제시 함께 삭제되는 댓글
	public int replySelectDelete(ReplyVO replyVO);
}
