package com.eventi.left.member.service;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;

public interface MemberService{

	public int IdCheck(String inputId); //아이디 중복확인

	public int insertMember(MemberVO memberVO); //회원가입

	public String mailCheck(String email);  //메일 인증

	public ResponseEntity<JsonNode> crtfCheck(String name, String qNo); //자격증 진위확인

}
