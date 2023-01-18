package com.eventi.left.reply.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		//댓글 등록
		@PostMapping("/replyInsert")
		@ResponseBody
		public ReplyVO replyInsert(@RequestBody ReplyVO replyVO) {
			replyService.replyInsert(replyVO);
			ReplyVO reply = replyService.getReply(replyVO);
			return reply;
		}
		
		//댓글 수정
		@PostMapping("/replyUpdate")
		@ResponseBody
		public ReplyVO replyUpdate(@RequestBody ReplyVO replyVO) {
			replyService.replyUpdate(replyVO);
			return replyVO;
		}
		// 댓글 삭제
		@PostMapping("/replyDelete")
		@ResponseBody
		public int replyDelete(ReplyVO replyVO) {
			return service.replyDelete(replyVO); 
		}
}
