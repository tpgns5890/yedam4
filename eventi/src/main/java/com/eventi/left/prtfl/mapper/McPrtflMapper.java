package com.eventi.left.prtfl.mapper;

import java.util.List;

import com.eventi.left.prtfl.service.McPrtflVO;
import com.eventi.left.reply.service.ReplyVO;

public interface McPrtflMapper {
	public List<McPrtflVO> mcAll(McPrtflVO McPrtflVO);
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO);
	
	public List<ReplyVO> mcReply(ReplyVO replyVO);
}
