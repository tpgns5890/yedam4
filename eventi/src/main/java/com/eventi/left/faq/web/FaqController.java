package com.eventi.left.faq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventi.left.faq.service.FaqService;
import com.eventi.left.faq.service.FaqVO;

@Controller
public class FaqController {
	
	@Autowired 
	FaqService faqService;
	
	//전체조회
	@RequestMapping(value = "/faqList", method=RequestMethod.GET)
	public String faqList(Model model, FaqVO faqVO) {
		model.addAttribute("faqList", faqService.getFaqList(faqVO));
		return "content/faq/faqList";
	}
	
}
