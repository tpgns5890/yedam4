package com.eventi.left.prtfl.service;

import java.util.List;

import com.eventi.left.reply.service.ReplyVO;

public interface McPrtflService {
	//사회자 전체 조회
	public List<McPrtflVO> mcAll(McPrtflVO McPrtflVO);
	
	//사회자 건별 조회
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO);
	
	//사회자 정보 입력
	public int mcInsert(McPrtflVO mcPrtflVO);
	
	//조회수 +1
	public int getSeq(McPrtflVO mcPrtflVO);
	
	//사회자 댓글 조회
	public List<ReplyVO> mcReply(ReplyVO replyVO);
	
	//사회자 수정
	public int mcUpdate(McPrtflVO mcPrtlVo);
}
