package com.eventi.left.admin.mapper;

import java.util.List;

import com.eventi.left.member.service.MemberVO;

public interface AdminMapper {
	
	//회원정보 수
	public int count(MemberVO memberVO);

	//전체조회
	public List<MemberVO> memberList(MemberVO memberVO);

}
