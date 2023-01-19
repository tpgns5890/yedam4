package com.eventi.left.contest.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.contest.service.WinnerService;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.design.service.DesignService;

@Controller
@RequestMapping("/winner")
public class WinnerController {

	@Autowired
	WinnerService service;
	@Autowired
	DesignService dService;

	// 우승자 전체리스트
	@RequestMapping(value = "/List")
	public String winnerList(Model model) {
		model.addAttribute("winnerList", service.winnerList(null));
		return "content/contest/winnerList";
	}

	// 등록처리
	@PostMapping("/update")
	@ResponseBody
	public String winnerUpdate(WinnerVO vo) {
		int r = service.updateWinner(vo);
		return "redirect:/contest/List"; //??
	}

}
