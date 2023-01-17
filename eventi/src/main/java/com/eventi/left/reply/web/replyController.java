package com.eventi.left.reply.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.reply.service.ReplyService;
import com.eventi.left.reply.service.ReplyVO;

@Controller
public class replyController {
	
	@Autowired
	ReplyService replyService;
	
	//해당 게시글의 댓글 및 대댓글 조회
		@RequestMapping(value="/replyList", method=RequestMethod.POST)
		@ResponseBody
		public List<ReplyVO> bReply(@RequestBody ReplyVO replyVo) {
			List<ReplyVO> reply = replyService.replyList(replyVo);
			return reply;
		}
}
