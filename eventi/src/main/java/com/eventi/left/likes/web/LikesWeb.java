package com.eventi.left.likes.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;

@Controller
@RequestMapping("/likes")
public class LikesWeb {

	@Autowired
	LikesService service;

	// 좋아요 전체리스트
	@RequestMapping(value = "/List")
	public String likeList(Model model) {
		model.addAttribute("likeList", service.likeList(null));
		return "content/likes/likeList";
	}

	// 좋아요 추가
	@PostMapping("/lInsert")
	@ResponseBody
	public int likeInsert(LikesVO LikesVO) {
		return service.likeInsert(LikesVO);
	}

	// 좋아요 취소
	@PostMapping("/lDelete")
	@ResponseBody
	public int likeDelete(LikesVO LikesVO) {
		return service.likeDelete(LikesVO);
	}

}
