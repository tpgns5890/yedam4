package com.eventi.left.contest.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.contest.service.ContestService;

@Controller
@RequestMapping("/contest")
public class ContestController {

	@Autowired
	ContestService service; 

	//공모전 전체리스트
	@RequestMapping(value = "/List")
	public String contestList(Model model) {
		model.addAttribute("contestList", service.contestList(null));
		return "content/contest/contestList";
	}
	
}
