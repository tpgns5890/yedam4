package com.eventi.left.prtfl.service;

import java.util.List;

import com.eventi.left.reply.service.ReplyVO;

public interface McPrtflService {
	//사회자 전체 조회
	public List<McPrtflVO> mcAll(McPrtflVO McPrtflVO);
	
	//사회자 건별 조회
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO);
	
	//사회자 댓글 조회
	public List<ReplyVO> mcReply(ReplyVO replyVO);
}
