package com.eventi.left.member.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.eventi.left.common.CodeVO;
import com.fasterxml.jackson.databind.JsonNode;

public interface MemberService{

	public int IdCheck(String inputId); //아이디 중복확인

	public int insertMember(MemberVO memberVO); //회원가입
	
	public MemberVO getMember(String userId);

	public String mailCheck(String email);  //메일 인증

	public ResponseEntity<JsonNode> crtfCheck(String name, String qNo); //자격증 진위확인

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	public String findId(String name, String email); //아이디 찾기

	public String findPw(String id, String name, String email); //비밀번호 찾기

	public List<CodeVO> getCountry();

	public List<CodeVO> getType();

	public int insertBusiMember(MemberVO memberVO);
	
	public boolean memberPwCheck(MemberVO memberVO); //회원정보 수정,탈퇴를 위한 비밀번호체크.

	public int userStateUpdate(MemberVO memberVO); //회원권한 변경.
	
	public int userUpdate(MemberVO memberVO); //회원정보 변경.
	
	public MemberVO busiSelect(String userId); //업체회원 조회

}
