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
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		System.out.println(user);
	    String sessionId = user.getUserId();
		messageVO.setChaId("CH01");
		messageVO.setToId("test03");
		messageVO.setFromId(sessionId);
		
		return mapper.msgInsert(messageVO);
	}

}
