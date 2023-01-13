package com.eventi.left.bboard.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.bboard.service.BboardService;
import com.eventi.left.bboard.service.BboardVO;
import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;

@Controller
@RequestMapping("/bboard")
public class BboardController {
	@Autowired BboardService bboardService;
	@Autowired LikesService likeService;
	
	//전체조회
	@GetMapping("/bList")
	public String bList(Model model, BboardVO bboardVO) {
		model.addAttribute("bList", bboardService.bboardList(bboardVO));
		model.addAttribute("type", bboardVO.getType());
		return "content/bboard/bList";
	}
	
	//정렬 전체 조회
	@PostMapping("/bList")
	@ResponseBody
	public List<BboardVO> bSelectList(Model model, BboardVO bboardVO) {
		model.addAttribute("type", bboardVO.getType());
		List<BboardVO> list = bboardService.bboardList(bboardVO);
		return list;
	}
	
	//좋아요 전체 조회
	@PostMapping("/bLikeList")
	@ResponseBody
	public List<BboardVO> bLikeList(BboardVO bboardVO){
		System.out.println(bboardVO.getWriter());
		List<BboardVO> list = bboardService.bboardLike(bboardVO);
		return list;
	}
	
	//단건조회
	@GetMapping("/bSelect")
	public String bSelect(Model model, BboardVO bboardVO, LikesVO likesVO) {
		likesVO.setUserId("사용자1");
		likesVO.setTargetNo(bboardVO.getBBoardNo());
		model.addAttribute("like", likeService.getLike(likesVO));
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
		bboardService.bboardInsert(bboardVO, uploadFile);
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
	public String bFreeUpdateForm(BboardVO bboardVO) {
		bboardService.bboardUpdate(bboardVO);
		return "redirect:/bboard/bSelect?bBoardNo=" + bboardVO.getBBoardNo();
	}
	
	//게시글 삭제
	@GetMapping("/bDelete")
	public String bDeleteForm(Model model, BboardVO bboardVO) {
		String type = bboardVO.getType();
		bboardService.bboardDelete(bboardVO);
		
		return "redirect:/bboard/bList?type=" + type;
	}
	
}
