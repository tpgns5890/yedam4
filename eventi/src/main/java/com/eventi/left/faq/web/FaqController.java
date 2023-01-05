package com.eventi.left.faq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventi.left.faq.mapper.FaqMapper;

@Controller
public class FaqController {
	
	@Autowired 
	FaqMapper faqMapper;
	
	@RequestMapping(value = "/faqList", method=RequestMethod.GET)
	public String faqList(Model model) {
		model.addAttribute("faqList", faqMapper.getFaqList(null));
		return "faqList";
	}
	
}
