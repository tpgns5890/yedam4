package com.eventi.left.member.mapper;

import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;

public interface MemberMapper {

	public int IdCheck(String inputId); //아이디 중복 확인

	public int insertMember(MemberVO memberVO); //일반회원 회원가입

	public void insertCrtf(CrtfVO crVO);

}
