package com.eventi.left.likes.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;
import com.eventi.left.member.service.MemberVO;

@Controller
@RequestMapping("/likes")
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

	// 로그인 회원의 좋아요 조회
	@PostMapping("/List")
	@ResponseBody
	public Map<String, Object> userlikeList(LikesVO LikesVO, PagingVO paging) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();

		// 리턴할 최종Map(contest,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("contest", service.userlikeList(LikesVO, sessionId));
		result.put("paging", paging);
		
		return result;
	}

}
