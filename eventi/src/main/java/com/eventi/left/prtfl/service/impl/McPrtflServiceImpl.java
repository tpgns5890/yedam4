package com.eventi.left.prtfl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.prtfl.mapper.McPrtflMapper;
import com.eventi.left.prtfl.service.McPrtflService;
import com.eventi.left.prtfl.service.McPrtflVO;
import com.eventi.left.reply.service.ReplyVO;

@Service
public class McPrtflServiceImpl implements McPrtflService{
	@Autowired McPrtflMapper mcPrtflMapper;
		
	//전체조회
	@Override
	public List<McPrtflVO> mcAll(McPrtflVO McPrtfVO) {
		return mcPrtflMapper.mcAll(McPrtfVO);
	}
	
	//건별조회
	@Override
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO) {
		return mcPrtflMapper.mcSelect(mcPrtflVO);
	}

	//댓글 전체조회
	@Override
	public List<ReplyVO> mcReply(ReplyVO replyVO) {
		return mcPrtflMapper.mcReply(replyVO);
	}

	//사회자 입력
	@Override
	public int mcInsert(McPrtflVO mcPrtflVO) {
		return mcPrtflMapper.mcInsert(mcPrtflVO);
	}
	
	//조회수 +1
	@Override
	public int getSeq(McPrtflVO mcPrtflVO) {
		return mcPrtflMapper.getSeq(mcPrtflVO);
	}

	@Override
	public int mcUpdate(McPrtflVO mcPrtlVo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
