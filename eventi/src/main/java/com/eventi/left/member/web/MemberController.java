package com.eventi.left.member.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.common.CodeVO;
import com.eventi.left.member.service.MemberService;
import com.eventi.left.member.service.MemberVO;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	// 이용약관 동의페이지 이동
	@RequestMapping(value = "/memQualification")
	public String qualification() {
		return "content/member/qualification";
	}

	// 일반회원 가입페이지 이동
	@RequestMapping(value = "/memNormalSignUp")
	public String normalSignUpPage() {
		return "content/member/normalSignUp";
	}

	// 업체회원 가입페이지 이동
	@RequestMapping(value = "/memBusiSignUp")
	public String busiSignUpPage(Model model, CodeVO codeVO) {
		model.addAttribute("busiArea", service.getCountry());
		model.addAttribute("busiStyle", service.getType());
		
		return "content/member/busiSignUp";
	}
	
	//아이디 찾기 페이지 이동
	@RequestMapping(value= "/findIdPage")
	public String findIdPage() {
		return "content/member/findId";
	}
	
	//아이디 찾기 메소드
	@RequestMapping(value= "/findId")
	@ResponseBody
	public String findid(String name, String email) {
		String result = service.findId(name, email);
		return result;
	}
	
	//비밀번호 찾기 페이지 이동
	@RequestMapping(value= "/findPwPage")
	public String findPwPage() {
		return "content/member/findPw";
	}
	
	//비밀번호 찾기 메소드
	@RequestMapping(value= "/findPw")
	@ResponseBody
	public String findPw(String id, String name, String email) {
		String result = service.findPw(id, name, email);
		if(result=="전송완료!") {
			return "전송완료!";
		}
		return "입력한 정보와 일치하는 회원이 없습니다.";
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
		System.out.println(responseEntity);
		return responseEntity;
	}
	
	//일반회원가입 처리
	@PostMapping("/insertMember")
	@ResponseBody
	public String insertMember(@RequestBody MemberVO memberVO) {
		memberVO.setUserPassword(passwordEncoder.encode(memberVO.getPassword()));
		service.insertMember(memberVO);
		return "redirect:/login";
	}
	//업체회원가입 처리
		@PostMapping("/insertBusiMember")
		@ResponseBody
		public String insertBusiMember(@RequestBody MemberVO memberVO) {
			memberVO.setUserPassword(passwordEncoder.encode(memberVO.getPassword()));
			System.out.println(memberVO);
			service.insertBusiMember(memberVO);
			return "redirect:/login";
		}
	
	//로그인페이지 이동
	@RequestMapping(value="/loginPage")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception,
			Model model,HttpServletRequest request) throws Exception {
		String uri = request.getHeader("Referer");
	    if (uri != null && !uri.contains("/loginPage")) {
	        request.getSession().setAttribute("prevPage", uri);
	    }
		/* 에러와 예외를 모델에 담아 view resolve */
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
        return "content/member/loginPage";
    }

}
