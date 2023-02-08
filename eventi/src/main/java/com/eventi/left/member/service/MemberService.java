package com.eventi.left.member.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
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

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, LockedException;

	public String findId(MemberVO memberVO); //아이디 찾기

	public String findPw(String id, String name, String email); //비밀번호 찾기
	
	public int userPwUpdate(String id, String password); //비밀번호 변경.

	public List<CodeVO> getCountry();

	public List<CodeVO> getType();

	public int insertBusiMember(MemberVO memberVO);
	
	public boolean memberPwCheck(MemberVO memberVO); //회원정보 수정,탈퇴를 위한 비밀번호체크.

	public int userStateUpdate(MemberVO memberVO); //회원권한 변경.
	
	public int userUpdate(MemberVO memberVO); //회원정보 변경.
	
	public int bankUpdate(MemberVO memberVO); // 계좌번호 변경.
	
	public MemberVO busiSelect(String userId); //업체회원 조회
	
	public int insertCrtf(CrtfVO crVO); //승급신청 자격증추가
	
	public List<CrtfVO> crtfSelect(String userId); // 회원의 자격증 조회

	public List<CodeVO> getBankCode();

}
