package com.eventi.left.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.contest.service.ContestService;
import com.eventi.left.contest.service.WinnerService;
import com.eventi.left.design.service.DesignService;

@Controller
@RequestMapping("/winner")
public class WinnerController {

	@Autowired
	WinnerService service;
	@Autowired
	DesignService dService;

	//우승자 전체리스트
	@RequestMapping(value = "/List")
	public String winnerList(Model model) {
		model.addAttribute("winnerList", service.winnerList(null));
		return "content/contest/winnerList";
	}
	
	/*
	 * // 등록화면이동
	 * 
	 * @GetMapping("/insert") public String contestInsert(Model model,String cNo) {
	 * 
	 * return "content/contest/winnerInsertForm"; }
	 */

	
}
