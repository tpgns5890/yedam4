package com.eventi.left.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.member.service.MemberService;
import com.eventi.left.member.service.MemberVO;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

	@Autowired
	MemberService service;

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
		String userId = user.getUserId();
		// 권한이 업체인경우
		if (user.getAuth().equals("ROLE_BUSI")) {
			model.addAttribute("user", service.busiSelect(userId));
			// 권한이 일반회원인경우
		} else {
			model.addAttribute("user", service.getMember(userId));
		}

		return "content/myPage/userMypage";
	}

	// 회원정보 수정폼
	@GetMapping("/userUpdateForm")
	public String userUpdate(Model model, MemberVO vo) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");

		// 회원등급이 업체인경우
		if (user.getAuth().equals("ROLE_BUSI")) {
			System.out.println("======================");
			System.out.println(service.busiSelect(user.getUserId()));
			System.out.println("======================");
			model.addAttribute("user", service.busiSelect(user.getUserId()));
		}else {
			model.addAttribute("user", service.getMember(user.getUserId()));
		}

		return "content/myPage/userUpdate";
	}

	// 회원정보 수정처리
	@PostMapping("/userUpdate")
	@ResponseBody
	public int userUpdate(@RequestBody MemberVO vo) {
		return service.userUpdate(vo);
	}

	// 회원비밀번호 확인
	@PostMapping("/userPwCheck")
	@ResponseBody
	public boolean userPwCheck(MemberVO vo) {
		return service.memberPwCheck(vo);
	}

	// 회원탈퇴(권한변경)
	@PostMapping("/userStateUpdate")
	@ResponseBody
	public int userStateUpdate(MemberVO vo) {
		return service.userStateUpdate(vo);
	}

	// 승급신청
	@GetMapping("/userUpgrade")
	public String userUpgrade() {
		return "content/myPage/userUpgrade";
	}

}
