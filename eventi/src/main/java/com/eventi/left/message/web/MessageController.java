package com.eventi.left.message.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.eventi.left.message.service.Greeting;
import com.eventi.left.message.service.HelloMessage;
import com.eventi.left.message.service.MessageService;
import com.eventi.left.message.service.MessageVO;

@Controller
public class MessageController {
	@Autowired MessageService messageService;
	
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
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message, MessageVO messageVO) throws Exception {
		//Thread.sleep(1000);
		messageVO.setMsgCntn(message.getName());
		//messageService.msgInsert(messageVO);
		System.out.println(messageVO);
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
}
