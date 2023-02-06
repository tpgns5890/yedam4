package com.eventi.left.review.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.review.service.ReviewService;
import com.eventi.left.review.service.ReviewVO;

@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	ReviewService reviewService;

	//내가 작성한 후기 전체 조회
	@GetMapping("/List")
	public String reviewList(Model model) {
		model.addAttribute("reviewList", reviewService.reviewList(null));
		return "content/review/reviewList";
	}
	
	//후기 등록
	@PostMapping("/reviewInsert")
	@ResponseBody
	public int reviewInsert(ReviewVO reviewVO) {
		return reviewService.reviewInsert(reviewVO);
	}
	
	//후기 정렬
	@PostMapping("/List")
	@ResponseBody
	public List<ReviewVO> reviewLists(@RequestBody ReviewVO reviewVO){
		return reviewService.getReview(reviewVO);
	}
}
