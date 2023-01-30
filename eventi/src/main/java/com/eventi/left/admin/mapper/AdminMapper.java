package com.eventi.left.admin.mapper;

import java.util.HashMap;
import java.util.List;

import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.punish.service.PunishVO;

public interface AdminMapper {
	
	//회원정보 수
	public int count(MemberVO memberVO);

	//회원 조회
	public List<MemberVO> memberList(MemberVO memberVO);

	//자격증 수
	public int certCount(CrtfVO crtfVO);

	//자격증 조회
	public List<CrtfVO> certList(CrtfVO crtfVO);

	//자격증 업데이트
	public int crtfUpdate(CrtfVO crtfVO);

	//공모전 조회
	public List<HashMap<String, Object>> contestList(ContestVO contVO);

	//공모전 수
	public int contCount();

	//신고회원 수
	public int punishCount(PunishVO punishVO);

	//신고 조회
	public List<PunishVO> punishList(PunishVO punishVO);

}
