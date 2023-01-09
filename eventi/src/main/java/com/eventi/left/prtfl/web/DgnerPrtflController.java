package com.eventi.left.prtfl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.prtfl.service.DgnerPrtflService;
import com.eventi.left.prtfl.service.DgnerPrtflVO;

@Controller
@RequestMapping("/prtfl")
public class DgnerPrtflController {
	@Autowired DgnerPrtflService service;
	
	@RequestMapping("/dgnerList")
	public String dgnerList(Model model, DgnerPrtflVO dgnerPrtflVO) {
		System.out.println(dgnerPrtflVO.getUserId());
		model.addAttribute("dgnerList", service.dgnerSelect(dgnerPrtflVO));
		return "content/prtfl/dgnerList";
	}
}
