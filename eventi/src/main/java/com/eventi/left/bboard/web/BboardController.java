package com.eventi.left.bboard.web;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventi.left.bboard.service.BboardService;
import com.eventi.left.bboard.service.BboardVO;

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
		model.addAttribute("type", bboardVO.getType());
		return "content/bboard/bInsert";
	}
	
	//게시글 등록
	@PostMapping("/bInsert")
	public String bFreeInsertForm(BboardVO bboardVO, MultipartFile uploadFile) {
		
		System.out.println(bboardVO.getCntn());
		
		// 사진 등록
		String realFolder = "/files/bboard";
		File dir = new File(realFolder);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		String img = uploadFile.getOriginalFilename();
		System.out.println(img);
		
		bboardVO.setImg(img);
		
		bboardService.bboardInsert(bboardVO);
		
		return "redirect:/bboard/bList?type=" + bboardVO.getType();
	}
	
	//게시글 수정폼 이동
	@GetMapping("/bUpdate")
	public String bUpdate(Model model, BboardVO bboardVO) {
		model.addAttribute("bSelect", bboardService.bboardSelect(bboardVO));
		return "content/bboard/bUpdate";
	}
	
	//게시글 수정
	@PostMapping("/bUpdate")
	@ResponseBody
	public int bFreeUpdateForm(BboardVO bboardVO) {
		return bboardService.bboardUpdate(bboardVO);
	}
	
	//게시글 삭제
	@GetMapping("/bDelete")
	public String bDeleteForm(Model model, BboardVO bboardVO) {
		String type = bboardVO.getType();
		System.out.println(type);
		bboardService.bboardDelete(bboardVO);
		
		return "redirect:/bboard/bList?type=" + type;
	}
	
}
