package com.eventi.left.faq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventi.left.faq.service.FaqService;
import com.eventi.left.faq.service.FaqVO;
import com.eventi.left.notice.service.NoticeVO;

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
	
	//구인등록폼이동
	@RequestMapping(value = "/faqInsert", method=RequestMethod.GET) 
	public String faqInsert(Model model) {
		model.addAttribute("nextNo", faqService.getSeq());
		return "content/faq/faqInsert";
	}
			
	//게시글 등록
	@PostMapping("/faqInsert")
	public String faqInsert(FaqVO faqVO) {
		faqService.faqInsert(faqVO);
		return "redirect:/faqList";
	}
	
	//게시글 삭제
	@GetMapping("/faqDelete") 
	public String faqDelete(FaqVO faqVO) {
		faqService.faqDelete(faqVO);
		return "redirect:/faqList"; 
	}	
}
