package com.eventi.left.member.web;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;

	@Autowired
	private JavaMailSender mailSender;

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
	public String mailCheckGET(String email) throws Exception{

		/* 인증번호(난수) 생성 */
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;

		/* 이메일 보내기 */
		String setFrom = "yedam4eventi@gmail.com";
		String toMail = email;
		String title = "EVENTI 회원가입 인증 이메일 입니다.";
		String content = "EVENTI를 방문해주셔서 감사합니다." + "<br><br>" + "인증 번호는 " + checkNum + "입니다." + "<br>"
				+ "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String num = Integer.toString(checkNum);
		return num;	//임의의 변수 6자리 리턴
	}
}
