package com.eventi.left.review.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.review.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewWeb {
	
	
	@Autowired
	ReviewService service;

	//후기 전체리스트
	@RequestMapping(value = "/List")
	public String reviewList(Model model) {
		model.addAttribute("reviewList", service.reviewList(null));
		return "content/review/reviewList";
	}

}
