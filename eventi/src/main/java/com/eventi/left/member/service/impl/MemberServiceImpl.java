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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.eventi.left.common.CodeVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.member.mapper.MemberMapper;
import com.eventi.left.member.service.BusiVO;
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
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	// 회원단건조회
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
		memberVO.setAuth("ROLE_MEM");
		int i = mapper.insertMember(memberVO);
		for (CrtfVO crVO : crtfVO) {
			crVO.setUserId(memberVO.getUserId());
			mapper.insertCrtf(crVO);
		}
		return i;
	}

	// 업체회원 회원가입
	@Override
	public int insertBusiMember(MemberVO memberVO) {
		memberVO.setAuth("ROLE_BUSI");
		int i = mapper.insertMember(memberVO);
		BusiVO busiVO = memberVO.getBusi();
		mapper.insertBusi(busiVO);
		return i;
	}

	// 메일 인증
	@Override
	public String mailCheck(String email) {
		/* 인증번호(난수) 생성 */
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;

		String title = "EVENTI 회원가입 인증 이메일 입니다.";
		String content = "EVENTI를 방문해주셔서 감사합니다." + "<br><br>" + "인증 번호는 " + checkNum + "입니다." + "<br>"
				+ "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		mailing(email, title, content);

		String num = Integer.toString(checkNum);
		return num; // 임의의 변수 6자리 리턴
	}

	/* ajax 함수내에서 cors 우회가 불가능하여 서버쪽에서 request 요청을 만들어 CORS 해결 */
	/* RestTemplate 사용 */
	/* 자격증 진위확인 */
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

	// userdetails
	@Override
	public UserDetails loadUserByUsername(String username) throws AuthenticationException {
		MemberVO vo = mapper.getMember(username);
		if (vo == null) {
			throw new UsernameNotFoundException("x");
		}
		return vo;
	}

	// 아이디 찾기
	@Override
	public String findId(String name, String email) {
		return mapper.findId(name, email);
	}

	// 비밀번호 찾기
	@Override
	public String findPw(String id, String name, String email) {
		int n = mapper.findPwCheck(id, name, email);
		if (n == 1) {
			/* 인증번호(난수) 생성 */
			Random random = new Random();
			int checkNum = random.nextInt(888888) + 111111;
			mapper.updatePw(id, passwordEncoder.encode(Integer.toString(checkNum)));
			String title = "EVENTI 임시비밀번호 확인 메일입니다.";
			String content = "EVENTI를 방문해주셔서 감사합니다." + "<br><br>" + "임시비밀번호는 " + checkNum + "입니다." + "<br>"
					+ "해당 임시비밀번호를 이용해 로그인 후 비밀번호를 변경하여 주세요.";
			mailing(email, title, content);
			return "전송완료!";
		} else {
			return "failed!";
		}
	}

	/* 이메일 보내기 */
	public void mailing(String email, String setTitle, String setContent) {
		String setFrom = "yedam4eventi@gmail.com";
		String toMail = email;
		String title = setTitle;
		String content = setContent;
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
	}

	// 행사지역 불러오기
	@Override
	public List<CodeVO> getCountry() {
		return mapper.getCountry();
	}

	// 행사유형 불러오기
	@Override
	public List<CodeVO> getType() {
		return mapper.getType();
	}

	// 회원정보 수정,탈퇴를 위한 비밀번호체크.
	@Override
	public boolean memberPwCheck(MemberVO vo) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String userPassword = user.getUserPassword();

		// 입력값,기존 암호화된DB 매칭 boolean값 반환
		return passwordEncoder.matches(vo.getUserPassword(), userPassword);
	}

	// 회원권한 변경.
	@Override
	public int userStateUpdate(MemberVO memberVO) {
		return mapper.userStateUpdate(memberVO);
	}

	// 회원정보 수정.
	@Override
	public int userUpdate(MemberVO vo) {
		
		//멤버기본정보 업데이트
		int result = mapper.userUpdate(vo);
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		// 권한이 업체인경우 추가업데이트
		if (user.getAuth().equals("ROLE_BUSI")) {
			BusiVO busiVO = vo.getBusi();
			result = mapper.busiUpdate(busiVO);
		}
		return result;
	}

	// 업체회원 정보 조회
	@Override
	public MemberVO busiSelect(String userId) {
		return mapper.busiSelect(userId);
	}

	@Override
	public int bankUpdate(MemberVO memberVO) {
		return mapper.bankUpdate(memberVO);
	}

}
