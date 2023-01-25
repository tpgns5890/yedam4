package com.eventi.left.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.common.SessionUtil;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.message.mapper.MessageMapper;
import com.eventi.left.message.service.MessageService;
import com.eventi.left.message.service.MessageVO;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired MessageMapper mapper;
	
	@Override
	public List<MessageVO> msgList(MessageVO messageVO) {
		return mapper.msgList(messageVO);
	}

	@Override
	public int msgInsert(MessageVO messageVO) {
		System.out.println("호출");
		return mapper.msgInsert(messageVO);
	}

}
