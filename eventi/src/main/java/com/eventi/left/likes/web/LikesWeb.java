package com.eventi.left.likes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.likes.service.LikesService;

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

}
