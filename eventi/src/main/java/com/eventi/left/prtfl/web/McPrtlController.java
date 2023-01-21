package com.eventi.left.prtfl.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.prtfl.service.McPrtflService;
import com.eventi.left.prtfl.service.McPrtflVO;
import com.eventi.left.reply.service.ReplyVO;

@Controller
@RequestMapping("/prtfl")
public class McPrtlController {
	@Autowired McPrtflService mcPrtflService;
	
	//사회자 전체 조회
	@RequestMapping("/mcList")
	public String mcList(Model model, McPrtflVO mcPrtflVO) {
		model.addAttribute("mcList", mcPrtflService.mcAll(mcPrtflVO));
		return "content/prtfl/mcList";
	}
	
	//사회자 단건 조회
	@RequestMapping("/mcSelect")
	public String mcSelect(Model model, McPrtflVO mcPrtflVO, ReplyVO replyVO) {
		model.addAttribute("mcSelect", mcPrtflService.mcSelect(mcPrtflVO));
		return "content/prtfl/mcSelect";
	}
	
	//해당 게시글의 댓글 및 대댓글 조회
	@PostMapping("/mcReply")
	@ResponseBody
	public List<ReplyVO> mcReply(@RequestBody ReplyVO vo) {
		List<ReplyVO> reply = mcPrtflService.mcReply(vo);
		return reply;
	}
	
	//사회자입력폼으로 이동
	@GetMapping("/mcInsert")
	public String mcInsert(McPrtflVO mcPrtlVo) {
		return "content/prtfl/mcInsert";
	}
	
	//사회자 입력
	@PostMapping("/mcInsert")
	@ResponseBody
	public int mcInsertFrm(@RequestBody McPrtflVO mcPrtflVO) {
		return mcPrtflService.mcInsert(mcPrtflVO);
	}
	
}
