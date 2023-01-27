package com.eventi.left.admin.mapper;

import java.util.List;

import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;

public interface AdminMapper {
	
	//회원정보 수
	public int count(MemberVO memberVO);

	//회원 조회
	public List<MemberVO> memberList(MemberVO memberVO);

	//자격증 수
	public int certCount(CrtfVO crtfVO);

	//자격증 조회
	public List<CrtfVO> certList(CrtfVO crtfVO);

}
