package com.eventi.left.bboard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eventi.left.bboard.service.BboardService;
import com.eventi.left.bboard.service.BboardVO;

@Controller
@RequestMapping("/bboard")
public class BboardController {
	@Autowired BboardService bboardService;
	
	//전체조회
	@GetMapping("/bList")
	public String bList(Model model, BboardVO bboardVO) {
		model.addAttribute("bList", bboardService.bboardList(bboardVO));
		model.addAttribute("type", bboardVO.getType());
		return "content/bboard/bList";
	}
	
	//단건조회
	@GetMapping("/bSelect")
	public String bSelect(Model model, BboardVO bboardVO) {
		model.addAttribute("bSelect", bboardService.bboardSelect(bboardVO));
		return "content/bboard/bSelect";
	}
	
	//게시글 등록
	@GetMapping("/bInsert")
	public String bInsert(BboardVO bboardVO) {
		if(bboardVO.getType().equals("T04")) {
			return "content/bboard/bFreeInsert";
		} else {
			return "content/bboard/bMemInsert";
		}
	}
	
}
