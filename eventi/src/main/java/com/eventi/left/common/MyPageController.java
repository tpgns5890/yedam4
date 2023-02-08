package com.eventi.left.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.member.service.CrtfVO;
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
	public String userUpdate(Model model) {

		// 양식코드 모델
		model.addAttribute("busiArea", service.getCountry());
		model.addAttribute("busiStyle", service.getType());

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

		return "content/myPage/userUpdate";
	}

	// 회원정보 수정처리(일반 및 업체)
	@PostMapping("/userUpdate")
	@ResponseBody
	public int userUpdate(@RequestBody MemberVO vo) {
		return service.userUpdate(vo);
	}

	// 회원 계좌정보 변경
	@PostMapping("/bankUpdate")
	@ResponseBody
	public MemberVO bankUpdate(MemberVO vo) {
		service.bankUpdate(vo);
		return service.getMember(vo.getUserId());
	}

	// 회원비밀번호 확인
	@PostMapping("/userPwCheck")
	@ResponseBody
	public boolean userPwCheck(MemberVO vo) {
		return service.memberPwCheck(vo);
	}
	//회원비밀번호 변경
	@PostMapping("/userPwUpdate")
	@ResponseBody
	public int userPwUpdate(String userId, String passwd) {
		System.out.println(passwd);
		System.out.println(userId);
		return service.userPwUpdate(userId, passwd);
	}

	// 회원탈퇴(권한변경)
	@PostMapping("/userStateUpdate")
	@ResponseBody
	public int userStateUpdate(MemberVO vo) {
		return service.userStateUpdate(vo);
	}

	// 승급신청
	@GetMapping("/userUpgradeForm")
	public String userUpgrade(Model model) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String userId = user.getUserId();
		
		model.addAttribute("userCrtf", service.crtfSelect(userId));

		return "content/myPage/userUpgrade";
	}

	// 승급신청 자격증등록
	@PostMapping("userUpgrade")
	@ResponseBody
	public CrtfVO userInsertCrtf(CrtfVO crVO) {
		service.insertCrtf(crVO);
		return crVO;
	}
	// 승급신청 자격증삭제(요청중인 상태만)
	@PostMapping("userDeleteCrtf")
	@ResponseBody
	public int userDeleteCrtf(String crtfId) {
		return service.crtfDelete(crtfId);
	}

}
