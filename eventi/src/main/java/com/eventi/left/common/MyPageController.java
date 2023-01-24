package com.eventi.left.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.member.service.MemberVO;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

	// 마이페이지(첫페이지)
	@GetMapping("/test")
	public String contestList() {
		return "content/myPage/firstMyPage";
	}

	// 회원정보 조회
	@GetMapping("/userMypage")
	public String userMypage(Model model) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		model.addAttribute("user", user);
		return "content/myPage/userMypage";
	}

	// 회원정보 수정폼
	@GetMapping("/userUpdate")
	public String userUpdate(Model model) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		model.addAttribute("user", user);
		return "content/myPage/userUpdate";
	}

	// 승급신청
	@GetMapping("/userUpgrade")
	public String userUpgrade() {
		return "content/myPage/userUpgrade";
	}

}
