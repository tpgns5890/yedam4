package com.eventi.left.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventi.left.contest.mapper.ContestMapper;

@Controller
@RequestMapping("/contest")
public class ContestController {

	@Autowired
	ContestMapper Mapper;

	@RequestMapping(value = "/List")
	public String contestList(Model model) {
		model.addAttribute("contestList", Mapper.contestList(null));
		return "content/contest/contestList";
	}
}
