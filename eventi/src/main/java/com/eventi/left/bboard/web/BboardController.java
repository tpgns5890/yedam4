package com.eventi.left.bboard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eventi.left.bboard.service.BboardService;
import com.eventi.left.bboard.service.BboardVO;

@Controller
@RequestMapping("/bboard")
public class BboardController {
	@Autowired BboardService bboardService;
	
	@RequestMapping("/bList")
	public String bfList(Model model, BboardVO bboardVO, @RequestParam String type) {
		bboardVO.setType(type);
		model.addAttribute("bList", bboardService.bboardList(bboardVO));
		return "content/bboard/bList";
	}

}
