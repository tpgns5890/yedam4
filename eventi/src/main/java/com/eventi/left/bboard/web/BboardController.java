package com.eventi.left.bboard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventi.left.bboard.service.BboardService;
import com.eventi.left.bboard.service.BboardVO;

import groovy.lang.Delegate;

@Controller
@RequestMapping("/bboard")
public class BboardController {
	@Autowired BboardService bboardService;
	
	//전체조회
	@GetMapping("/bList")
	public String bList(Model model, BboardVO bboardVO) {
		model.addAttribute("bList", bboardService.bboardList(bboardVO));
		model.addAttribute("type", bboardVO.getType());
		return "content/bboard/bList";
	}
	
	//단건조회
	@GetMapping("/bSelect")
	public String bSelect(Model model, BboardVO bboardVO) {
		model.addAttribute("bSelect", bboardService.bboardSelect(bboardVO));
		return "content/bboard/bSelect";
	}
	
	//게시글 등록폼 이동
	@GetMapping("/bInsert")
	public String bInsert(Model model, BboardVO bboardVO) {
		if(bboardVO.getType().equals("T04")) {
			model.addAttribute("type", bboardVO.getType());
			return "content/bboard/bFreeInsert";
		} else {
			model.addAttribute("type", bboardVO.getType());
			return "content/bboard/bMemInsert";
		}
	}
	
	//게시글 등록
	@PostMapping("/bInsert")
	public String bFreeInsertForm(BboardVO bboardVO, RedirectAttributes rttr) {
		bboardService.bboardInsert(bboardVO);
		rttr.addFlashAttribute("result", "게시글 등록 완료!");
		
		return "redirect:/bboard/bList?type=T04";
	}
	
	//게시글 수정폼 이동
	@GetMapping("/bUpdate")
	public String bUpdate(Model model, BboardVO bboardVO) {
		model.addAttribute("bSelect", bboardService.bboardSelect(bboardVO));
		return "content/bboard/bUpdate";
	}
	
	//게시글 수정
	@PostMapping("/bUpdate")
	public String bFreeUpdateForm(BboardVO bboardVO, RedirectAttributes rttr) {
		bboardService.bboardUpdate(bboardVO);
		rttr.addFlashAttribute("result", "게시글 수정 완료!");
		
		return "redirect:/bboard/bList?type=T04";
	}
	
	//게시글 삭제
	@GetMapping("/bDelete")
	public String bDeleteForm(BboardVO bboardVO, RedirectAttributes rttr) {
		bboardService.bboardDelete(bboardVO);
		rttr.addFlashAttribute("result", "게시글 삭제 완료!");
		
		return "redirect:/bboard/bList?type=T04";
	}
	
}
