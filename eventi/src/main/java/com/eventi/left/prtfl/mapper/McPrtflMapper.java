package com.eventi.left.prtfl.mapper;

import java.util.List;

import com.eventi.left.prtfl.service.McPrtflVO;
import com.eventi.left.reply.service.ReplyVO;

public interface McPrtflMapper {
	//사회자 전체조회
	public List<McPrtflVO> mcAll(McPrtflVO McPrtflVO);
	
	//사회자 단건조회
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO);
	
	//사회자 등록 여부
	public int checkMc(McPrtflVO mcPrtflVO);
	
	//전체 건수
	public int count(McPrtflVO mcPrtflVO);
	
	//사회자별 댓글 조회
	public List<ReplyVO> mcReply(ReplyVO replyVO);
	
	//조회수 +1
	public int inqUpdate(McPrtflVO mcPrtflVO);
	
	//사회자 정보 입력
	public int mcInsert(McPrtflVO mcPrtflVO);
	
	//사회자 수정
	public int mcUpdate(McPrtflVO mcPrtlVO);
}
