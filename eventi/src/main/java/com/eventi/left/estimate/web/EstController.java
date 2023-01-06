package com.eventi.left.estimate.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventi.left.estimate.mapper.EstMapper;

@Controller
public class EstController {
	
	@Autowired
	EstMapper estMapper;
	
	@RequestMapping(value = "/estList")
	public String estList(Model model) {
		model.addAttribute("estList", estMapper.getEstList(null));
		return "content/estimate/estList";
	}
}
