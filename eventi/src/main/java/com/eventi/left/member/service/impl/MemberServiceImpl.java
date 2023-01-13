package com.eventi.left.member.service.impl;

import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.eventi.left.member.mapper.MemberMapper;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberService;
import com.eventi.left.member.service.MemberVO;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {

	@Autowired
	MemberMapper mapper;
	@Autowired
	private JavaMailSender mailSender;
	
	//회원단건조회
	@Override
	public MemberVO getMember(String userId) {
		return mapper.getMember(userId);
	}
	
	// 아이디 중복확인
	@Override
	public int IdCheck(String inputId) {
		return mapper.IdCheck(inputId);
	}

	// 일반회원 회원가입
	@Override
	public int insertMember(MemberVO memberVO) {
		List<CrtfVO> crtfVO = memberVO.getCrtfs();

		int i = mapper.insertMember(memberVO);
		for (CrtfVO crVO : crtfVO) {
			crVO.setUserId(memberVO.getUserId());
			mapper.insertCrtf(crVO);
		}
		return i;
	}

	// 메일 인증
	@Override
	public String mailCheck(String email) {
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
		return num; // 임의의 변수 6자리 리턴
	}

	/* ajax 함수내에서 cors 우회가 불가능하여 서버쪽에서 request 요청을 만들어 CORS 해결 */
	/* RestTemplate 사용 */
	@Override
	public ResponseEntity<JsonNode> crtfCheck(String name, String qNo) {
		String url = "http://data.kca.kr/api/v1/cq/certificate/check"; // api url
		String apiKey = "c2cbf7ef39edd94fd8d3ee127e1d8d4950f60a67446eed201154ec83a7689dcf"; // api 인증키

		// get parameter 담아주기
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url) // url
				.queryParam("apiKey", apiKey) // 인증키
				.queryParam("name", name) // 이름
				.queryParam("no", qNo); // 자격증발급번호
		RestTemplate restTpl = new RestTemplate();
		HttpHeaders headers = new HttpHeaders(); // 담아줄 header
		HttpEntity<Object> entity = new HttpEntity<>(headers); // http entity에 header 담아줌
		ResponseEntity<JsonNode> responseEntity = restTpl.exchange(builder.toUriString(), HttpMethod.GET, entity,
				JsonNode.class);
		JsonNode result = (JsonNode) responseEntity.getBody();

		return ResponseEntity.ok(result);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = mapper.getMember(username);
		return vo;
	}
}
