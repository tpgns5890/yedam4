package com.eventi.left.message.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.message.service.MessageService;
import com.eventi.left.message.service.MessageVO;

@Controller
public class MessageController {
	@Autowired MessageService messageService;
	@Autowired
	   private SimpMessagingTemplate template;
	
	//채팅방별 메시지
	@PostMapping("/msgList")
	@ResponseBody
	public List<MessageVO> msgList(@RequestBody MessageVO messageVO) {
		List<MessageVO> list = messageService.msgList(messageVO);
		return list;
	}
	
	//메시지 입력
	@GetMapping("/msgInsert")
	@ResponseBody
	public int msgInsert(MessageVO messageVO) {
		return messageService.msgInsert(messageVO);
	}
	
	//채팅연결
	@MessageMapping("/chat")
	public void message(MessageVO messageVO) throws Exception {
		//Thread.sleep(1000);
		//messageVO.setMsgCntn(message.getName());
		System.out.println(messageVO);
		messageService.msgInsert(messageVO);
		
		template.convertAndSend("/topic/chat/" + messageVO.getChaId(), messageVO);
	}
}
