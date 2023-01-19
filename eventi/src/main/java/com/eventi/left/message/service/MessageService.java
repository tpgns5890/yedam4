package com.eventi.left.message.service;

import java.util.List;

public interface MessageService {
	//채팅방별 전체대화 조회
	public List<MessageVO> msgList(MessageVO messageVO);
	
	//대화 입력
	public int msgInsert(MessageVO messageVO);
}
