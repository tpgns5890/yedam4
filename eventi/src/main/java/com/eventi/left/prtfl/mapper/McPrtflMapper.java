package com.eventi.left.prtfl.mapper;

import java.util.List;

import com.eventi.left.prtfl.service.McPrtflVO;
import com.eventi.left.reply.service.ReplyVO;

public interface McPrtflMapper {
	//사회자 전체조회
	public List<McPrtflVO> mcAll(McPrtflVO McPrtflVO);
	
	//사회자 단건조회
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO);
	
	//사회자별 댓글 조회
	public List<ReplyVO> mcReply(ReplyVO replyVO);
	
	//조회수 +1
	public int getSeq(McPrtflVO mcPrtflVO);
	
	//사회자 정보 입력
	public int mcInsert(McPrtflVO mcPrtflVO);
}
