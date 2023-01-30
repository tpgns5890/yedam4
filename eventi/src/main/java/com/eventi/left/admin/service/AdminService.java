package com.eventi.left.admin.service;

import java.util.HashMap;
import java.util.List;

import com.eventi.left.common.PagingVO;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.punish.service.PunishVO;

public interface AdminService {
	public List<MemberVO> memberList(MemberVO memberVO, PagingVO paging);  //회원 전체조회

	public int count(MemberVO memberVO);  //회원 수 카운트

	public List<CrtfVO> certList(CrtfVO crtfVO, PagingVO paging); //자격증 전체조회

	public int crtfUpdate(CrtfVO crtfVO); //자격증 승인

	public List<HashMap<String,Object>> contestList(ContestVO contVO, PagingVO paging); // 공모전 조회

	public List<PunishVO> punishList(PunishVO punishVO, PagingVO paging);

}
