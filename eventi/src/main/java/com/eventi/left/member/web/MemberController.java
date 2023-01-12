package com.eventi.left.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.member.service.MemberService;
import com.eventi.left.member.service.MemberVO;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class MemberController {

	@Autowired
	MemberService service;

	// 이용약관 동의페이지 이동
	@RequestMapping(value = "/memQualification")
	public String qualification() {
		return "content/member/qualification";
	}

	// 일반회원 가입페이지 이동
	@RequestMapping(value = "/memNormalSignIn")
	public String normalSignInPage() {
		return "content/member/normalSignIn";
	}

	// 업체회원 가입페이지 이동
	@RequestMapping(value = "/memBusiSignIn")
	public String busiSignInPage() {
		return "content/member/busiSignIn";
	}

	// 아이디 중복 확인
	@RequestMapping("/memIdCheck")
	public @ResponseBody int memIdCheck(String inputId) {
		int result = service.IdCheck(inputId);
		return result;
	}

	/* 이메일 인증 */
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {
		String num = service.mailCheck(email);
		return num;
	}

	
	/*자격증 진위확인 외부 api */
	@GetMapping("/qualCheck")
	public ResponseEntity<JsonNode> crtfCheck(String name, String qNo) {
		ResponseEntity<JsonNode> responseEntity = service.crtfCheck(name, qNo);
		return responseEntity;
	}
	
	//회원가입 처리
	@PostMapping("/insertMember")
	@ResponseBody
	public String insertMember(@RequestBody MemberVO memberVO) {
		service.insertMember(memberVO);
		return "완료";
	}
}
