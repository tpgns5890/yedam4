package com.eventi.left.message.mapper;

import java.util.List;

import com.eventi.left.message.service.MessageVO;

public interface MessageMapper {
	//채팅방별 전체대화 조회
	public List<MessageVO> msgList(MessageVO messageVO);
		
	//대화 입력
	public int msgInsert(MessageVO messageVO);
}
