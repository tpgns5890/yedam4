package com.eventi.left.promotion.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.job.service.JobBoardVO;
import com.eventi.left.promotion.service.PromotionService;
import com.eventi.left.promotion.service.PromotionVO;

@Controller
public class PromotionController {

	@Autowired 
	PromotionService proService;
	
	//홍보게시물 전체조회
	@RequestMapping(value = "/proList", method=RequestMethod.GET)
	public String noticeList(Model model) {
		model.addAttribute("proList", proService.proList(null));
		return "content/promotion/proList";
	}
	
	//게시글상세조회
		@RequestMapping(value = "/proDetail", method=RequestMethod.GET) 
		public String proDetail(Model model, PromotionVO promotionVO) {
			model.addAttribute("proDetail", proService.proDetail(promotionVO));
			return "content/promotion/proDetail";
		}
		
	//게시글등록폼이동
		@RequestMapping(value = "/proInsert", method=RequestMethod.GET) 
		public String proInsert() {
			return "content/promotion/proInsert";
		}
	
	//게시글 등록
		@PostMapping("/proInsert")
		public String proInsert(PromotionVO promotionVO, MultipartFile uploadFile) {
			proService.proInsert(promotionVO, uploadFile ); //값이 vo자동으로 저장
			return "redirect:/proList";
			}		
	
}
