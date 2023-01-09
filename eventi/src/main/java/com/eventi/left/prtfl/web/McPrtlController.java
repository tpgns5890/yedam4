package com.eventi.left.prtfl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.prtfl.service.McPrtflService;
import com.eventi.left.prtfl.service.McPrtflVO;

@Controller
@RequestMapping("/prtfl")
public class McPrtlController {
	@Autowired McPrtflService mcPrtflService;
	
	@RequestMapping("/mcList")
	public String mcList(Model model, McPrtflVO mcPrtflVO) {
		model.addAttribute("mcList", mcPrtflService.mcAll(mcPrtflVO));
		return "content/prtfl/mcList";
	}
	
	@RequestMapping("mcSelect")
	public String mcSelect(Model model, McPrtflVO mcPrtflVO) {
		model.addAttribute("mcSelect", mcPrtflService.mcSelect(mcPrtflVO));
		
		return "content/prtfl/mcSelect";
	}
}
