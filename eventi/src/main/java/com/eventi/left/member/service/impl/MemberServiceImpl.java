package com.eventi.left.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.member.mapper.MemberMapper;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberService;
import com.eventi.left.member.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired MemberMapper mapper;
	
	//아이디 중복확인
	@Override
	public int IdCheck(String inputId) {
		return mapper.IdCheck(inputId);
	}
	
	//일반회원 회원가입
		@Override
	public int insertMember(MemberVO memberVO) {
			List<CrtfVO> crtfVO = memberVO.getCrtfs();

			int i = mapper.insertMember(memberVO);
			for(CrtfVO crVO  : crtfVO) {
				crVO.setUserId(memberVO.getUserId());
				mapper.insertCrtf(crVO);
			}
		return i;
	}

}
