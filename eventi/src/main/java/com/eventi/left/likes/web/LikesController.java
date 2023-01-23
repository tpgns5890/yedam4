package com.eventi.left.likes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;

import groovy.util.logging.Log4j;

@Controller
@RequestMapping("/likes")
@Log4j
public class LikesController {

	@Autowired
	LikesService service;

	// 좋아요 추가
	@PostMapping("/lInsert")
	@ResponseBody
	public int likeInsert(LikesVO LikesVO) {
		System.out.println("연결완료");
		return service.likeInsert(LikesVO);
	}

	// 좋아요 취소
	@PostMapping("/lDelete")
	@ResponseBody
	public int likeDelete(LikesVO LikesVO) {
		System.out.println("연결완료");
		return service.likeDelete(LikesVO);
	}

}
