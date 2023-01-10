package com.eventi.left.casting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.casting.service.CastingService;
import com.eventi.left.casting.service.CastingVO;

@Controller
@RequestMapping("/casting")
public class CastingController {
	@Autowired CastingService service;
	
	@RequestMapping("/castingList")
	public String castingList(Model model, CastingVO castingVO) {
		model.addAttribute("casting", service.castingList(castingVO));
		return "content/casting/castingList";
	}
}
