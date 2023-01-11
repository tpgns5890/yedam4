package com.eventi.left.member.service;

public interface MemberService{

	public int IdCheck(String inputId); //아이디 중복확인

	public int insertMember(MemberVO memberVO);

}
