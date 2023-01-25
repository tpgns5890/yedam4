package com.eventi.left.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.notice.service.NoticeService;
import com.eventi.left.notice.service.NoticeVO;
import com.eventi.left.promotion.service.PromotionVO;

@Controller
public class NoticeController {

	@Autowired 
	NoticeService nocService;
	
	//게시물 전체조회
	@RequestMapping(value = "/nocList", method=RequestMethod.GET)
	public String noticeList(Model model, NoticeVO noticeVO, PagingVO paging) {
		model.addAttribute("nocList", nocService.noticeList(noticeVO, paging));
		model.addAttribute("paging", paging);
		return "content/notice/nocList";
	}
	
	//게시글상세조회로이동
	@RequestMapping(value = "/nocDetail", method=RequestMethod.GET) 
	public String nocDetail(Model model, NoticeVO noticeVO) {
		//조회수
		nocService.seeUp(noticeVO);
		//상세내용 
		model.addAttribute("nocDetail", nocService.nocDetail(noticeVO));
		return "content/notice/nocDetail";
	}
	
	//구인등록폼이동
	@RequestMapping(value = "/nocInsert", method=RequestMethod.GET) 
	public String nocInsert(Model model) {
		model.addAttribute("nextNo", nocService.getSeq());
		return "content/notice/nocInsert";
	}
		
	//게시글 등록
	@PostMapping("/nocInsert")
	//form으로 보냄
	public String nocInsert(NoticeVO noticeVO) {
		nocService.nocInsert(noticeVO);
		return "redirect:/nocList";
	}
		
	//게시글수정페이지로이동
	@RequestMapping(value = "/nocUpdate", method=RequestMethod.GET) 
	public String nocUpdate(Model model, NoticeVO noticeVO) {
		model.addAttribute("nocUpdate", nocService.nocDetail(noticeVO));
		return "content/notice/nocUpdate";
	}
			
	//게시글 수정
	@PostMapping("/nocUpdate")
	public String nocUpdate(NoticeVO noticeVO) {
		nocService.nocUpdate(noticeVO);
		return "redirect:/nocList";
	}
	
	//게시글 삭제
	@GetMapping("/nocDelete") 
	public String nocDelete(NoticeVO noticeVO) {
		nocService.nocDelete(noticeVO);
		return "redirect:/nocList"; 
	}	
}


