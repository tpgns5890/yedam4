package com.eventi.left.prtfl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.SessionUtil;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.member.service.MemberService;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.prtfl.service.BusiPrtflService;
import com.eventi.left.prtfl.service.BusiPrtflVO;
import com.eventi.left.review.service.ReviewService;
import com.eventi.left.review.service.ReviewVO;

@Controller
@RequestMapping("/prtfl")
public class BusiPrtflController {
	@Autowired BusiPrtflService busiPrtflService;
	@Autowired MemberService memberService;
	@Autowired ReviewService reviewService;
	
	//업체 전체리스트
	@RequestMapping("/busiList")
	public String busiList(Model model, BusiPrtflVO busiPrtflVO) {
		model.addAttribute("busiList", busiPrtflService.busiList(busiPrtflVO));
		return "content/prtfl/busiList";
	}
	
	//단건조회
	@GetMapping("/busiSelect")
	public String busiSelect(Model model, BusiPrtflVO busiPrtflVO, ReviewVO reviewVO) {
		model.addAttribute("busiSelect", busiPrtflService.busiSelect(busiPrtflVO));
		
		//후기
		reviewVO.setReviewTgt(busiPrtflVO.getUserId());
		reviewVO.setCategory("T08");
		model.addAttribute("reviews", reviewService.getReview(reviewVO));
		
		//후기 평균 별점, 전체건수
		model.addAttribute("recount", reviewService.getReviewAvg(reviewVO));		
		
		return "content/prtfl/busiSelect";
	}
	
	//업체 포트폴리오 등록 페이지 이동
	@GetMapping("/busiInsert")
	public String busiInsert(Model model) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		
		model.addAttribute("busi", memberService.busiSelect(user.getUserId()));
		return "content/prtfl/busiInsert";
	}
	
	//업체 등록
	@PostMapping("/busiInsert")
	public String busiInsertFrm(BusiPrtflVO busiPrtflVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		busiPrtflService.busiInsert(busiPrtflVO, filesVO, uploadFile);
		
		return "redirect:/prtfl/busiList";
	}
}
