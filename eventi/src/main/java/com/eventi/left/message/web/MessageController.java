package com.eventi.left.message.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.message.service.Greeting;
import com.eventi.left.message.service.HelloMessage;
import com.eventi.left.message.service.MessageService;
import com.eventi.left.message.service.MessageVO;

@Controller
public class MessageController {
	@Autowired MessageService messageService;
	@Autowired
	   private SimpMessagingTemplate template;
	
	@GetMapping("/msgList")
	public String msgList(Model model, MessageVO messageVO) {
		//model.addAttribute("msgs", messageService.msgList(messageVO));
		return "content/chatting/messageTest2";
	}
	
	@GetMapping("/msgInsert")
	@ResponseBody
	public int msgInsert(MessageVO messageVO) {
		return messageService.msgInsert(messageVO);
	}
	
	@MessageMapping("/chat")
	public void message(MessageVO messageVO) throws Exception {
		//Thread.sleep(1000);
		//messageVO.setMsgCntn(message.getName());
		System.out.println(messageVO);
		messageService.msgInsert(messageVO);
		
		template.convertAndSend("/topic/chat/" + messageVO.getChaId(), messageVO);
	}
}
