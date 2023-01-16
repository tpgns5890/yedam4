package com.eventi.left.promotion.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.promotion.service.PromotionService;
import com.eventi.left.promotion.service.PromotionVO;
import com.eventi.left.reply.service.ReplyVO;

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
		public String proDetail(Model model, PromotionVO promotionVO, ReplyVO replyVO) {
			model.addAttribute("proDetail", proService.proDetail(promotionVO));
			
			replyVO.setReplyTgt(promotionVO.getUserId());
			model.addAttribute("proReply", proService.proReply(replyVO));
			return "content/promotion/proDetail";
		}
		
	//댓글 조회 
		@RequestMapping(value="/proReply", method=RequestMethod.POST)
		@ResponseBody
		public List<ReplyVO> proReply(@RequestBody ReplyVO vo) {
			List<ReplyVO> a = proService.proReply(vo);
			return a;
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
	
	//게시글수정페이지로이동
		@RequestMapping(value = "/proUpdate", method=RequestMethod.GET) 
		public String proUpdate(Model model, PromotionVO promotionVO) {
			model.addAttribute("proUpdate", proService.proDetail(promotionVO));
			return "content/promotion/proUpdate";
		}
		
	//게시글 수정
		@PostMapping("/proUpdate")
		public String proUpdate(PromotionVO promotionVO) {
			proService.proUpdate(promotionVO);
			return "redirect:/proList";
		}

	//게시글 삭제
		@GetMapping("/proDelete") //th:onclick = location
		public String proDelete(PromotionVO promotionVO) {
			proService.proDelete(promotionVO);
			return "redirect:/proList"; 
		}
}
