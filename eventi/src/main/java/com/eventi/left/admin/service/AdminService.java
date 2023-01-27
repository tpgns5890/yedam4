package com.eventi.left.admin.service;

import java.util.List;

import com.eventi.left.common.PagingVO;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;

public interface AdminService {
	public List<MemberVO> memberList(MemberVO memberVO, PagingVO paging);

	public int count(MemberVO memberVO);

	public List<CrtfVO> certList(CrtfVO vo, PagingVO paging);
}
