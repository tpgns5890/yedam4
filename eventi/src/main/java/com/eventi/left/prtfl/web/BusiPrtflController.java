package com.eventi.left.prtfl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.prtfl.service.BusiPrtflService;
import com.eventi.left.prtfl.service.BusiPrtflVO;

@Controller
@RequestMapping("/prtfl")
public class BusiPrtflController {
	@Autowired BusiPrtflService busiPrtflService;
	
	@RequestMapping("/busiList")
	public String mcList(Model model, BusiPrtflVO vo) {
		model.addAttribute("busiList", busiPrtflService.busiList(vo));
		return "content/prtfl/busiList";
	
	}
}
