package com.eventi.left.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.admin.mapper.AdminMapper;
import com.eventi.left.admin.service.AdminService;
import com.eventi.left.common.CodeVO;
import com.eventi.left.common.PagingVO;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.punish.service.PunishVO;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired AdminMapper adminMapper;
	
	//회원전체조회
	@Override
	public List<MemberVO> memberList(MemberVO memberVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.count(memberVO));
		paging.setPageUnit(10);
		memberVO.setFirst(paging.getFirst());
		memberVO.setLast(paging.getLast());
		
		return adminMapper.memberList(memberVO);
	}

	//회원카운트
	@Override
	public int count(MemberVO memberVO) {
		return adminMapper.count(memberVO);
	}
	
	//자격증조회
	@Override
	public List<CrtfVO> certList(CrtfVO crtfVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.certCount(crtfVO));
		paging.setPageUnit(10);
		crtfVO.setFirst(paging.getFirst());
		crtfVO.setLast(paging.getLast());
		return adminMapper.certList(crtfVO);
	}

	//자격증 업테이트
	@Override
	public int crtfUpdate(CrtfVO crtfVO) {
		return adminMapper.crtfUpdate(crtfVO);
	}

	//공모전 조회
	@Override
	public List<HashMap<String, Object>> contestList(ContestVO contVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.contCount());
		paging.setPageUnit(10);
		contVO.setFirst(paging.getFirst());
		contVO.setLast(paging.getLast());
		return adminMapper.contestList(contVO);
	}
	
	//신고회원 조회
	@Override
	public List<PunishVO> punishList(PunishVO punishVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.punishCount(punishVO));
		paging.setPageUnit(10);
		punishVO.setFirst(paging.getFirst());
		punishVO.setLast(paging.getLast());
		return adminMapper.punishList(punishVO);
	}
	
	//회원 카테고리
	@Override
	public List<CodeVO> getMemberCat(CodeVO codeVO) {
		return adminMapper.getMemberCat(codeVO);
	}
	
	//게시글 카테고리
	@Override
	public List<CodeVO> getBoardCat(CodeVO codeVO) {
		return adminMapper.getBoardCat(codeVO);
	}

	//신고글 조회
	@Override
	public List<HashMap<String, Object>> punishBrdList(PunishVO punishVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.punishCount(punishVO));
		paging.setPageUnit(10);
		punishVO.setFirst(paging.getFirst());
		punishVO.setLast(paging.getLast());
		return adminMapper.punishBrdList(punishVO);
	}

	//회원신고 승인
	@Override
	public int banMemAccept(PunishVO punishVO) {
		adminMapper.banMember(punishVO.getTargetId());
		return adminMapper.banMemAccept(punishVO);
	}
	
	//회원신고 거절
	@Override
	public int banReject(PunishVO punishVO) {
		return adminMapper.banReject(punishVO);
	}

	//게시글신고 승인
	@Override
	public int banBrdAccept(PunishVO punishVO) {
		adminMapper.banMember(punishVO.getWriter());
		if(punishVO.getTargetCat()=="T01") {
			adminMapper.banBoard1(punishVO.getTargetId());
		}else if(punishVO.getTargetCat()=="T03") {
			adminMapper.banBoard2(punishVO.getTargetId());
		}else {
			adminMapper.banBoard3(punishVO.getTargetId());
		}
		return adminMapper.banBrdAccept(punishVO);
	}

	

}
