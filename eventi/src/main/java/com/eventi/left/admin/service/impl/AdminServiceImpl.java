package com.eventi.left.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.admin.mapper.AdminMapper;
import com.eventi.left.admin.service.AdminService;
import com.eventi.left.common.PagingVO;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;

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

}
