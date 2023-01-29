package com.eventi.left.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.admin.mapper.AdminMapper;
import com.eventi.left.admin.service.AdminService;
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
		paging.setPageUnit(3);
		crtfVO.setFirst(paging.getFirst());
		crtfVO.setLast(paging.getLast());
		return adminMapper.certList(crtfVO);
	}

	//자격증 업테이트
	@Override
	public int crtfUpdate(CrtfVO crtfVO) {
		return adminMapper.crtfUpdate(crtfVO);
	}

	@Override
	public List<HashMap<String, Object>> contestList(ContestVO contVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.contCount());
		paging.setPageUnit(3);
		contVO.setFirst(paging.getFirst());
		contVO.setLast(paging.getLast());
		return adminMapper.contestList(contVO);
	}

	@Override
	public List<PunishVO> punishList(PunishVO punishVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.punishCount(punishVO));
		paging.setPageUnit(1);
		punishVO.setFirst(paging.getFirst());
		punishVO.setLast(paging.getLast());
		return adminMapper.punishList(punishVO);
	}
}
