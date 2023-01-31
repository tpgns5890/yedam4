package com.eventi.left.admin.service;

import java.util.HashMap;
import java.util.List;

import com.eventi.left.common.CodeVO;
import com.eventi.left.common.PagingVO;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.punish.service.PunishVO;

public interface AdminService {
	public int count(MemberVO memberVO);  //회원 수 카운트
	
	public List<MemberVO> memberList(MemberVO memberVO, PagingVO paging);  //회원 조회
	public List<CrtfVO> certList(CrtfVO crtfVO, PagingVO paging); //자격증 조회
	public List<HashMap<String,Object>> contestList(ContestVO contVO, PagingVO paging); // 공모전 조회
	public List<PunishVO> punishList(PunishVO punishVO, PagingVO paging); //회원신고 조회
	public List<HashMap<String,Object>> punishBrdList(PunishVO punishVO, PagingVO paging); //신고게시글 조회

	public List<CodeVO> getMemberCat(CodeVO codeVO); //회원 카테고리
	public List<CodeVO> getBoardCat(CodeVO codeVO); //게시물 카테고리

	public int crtfUpdate(CrtfVO crtfVO); //자격증 승인

	public int banMemAccept(PunishVO punishVO); //회원신고 승인
	public int banBrdAccept(PunishVO punishVO); //게시글신고 승인

	public int banReject(PunishVO punishVO); //신고 거절



}
