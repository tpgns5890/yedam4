package com.eventi.left.reply.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.reply.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class replyController {
	
	@Autowired
	ReplyService service;

	// 댓글 전체리스트	
	@RequestMapping(value = "/List")
	public String replyList(Model model) {
		model.addAttribute("replyList", service.replyList(null));
		return "content/reply/replyList";
	}

}
