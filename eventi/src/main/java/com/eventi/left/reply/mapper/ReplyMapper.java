package com.eventi.left.reply.mapper;

import java.util.List;

import com.eventi.left.reply.service.ReplyVO;

public interface ReplyMapper {
	
	// 댓글
		public List<ReplyVO> replyList(ReplyVO ReplyVO); // 전체조회

		public ReplyVO getReply(ReplyVO ReplyVO); // 1건조회
		
	// 삭제
		public int replyDelete(ReplyVO ReplyVO);
}
