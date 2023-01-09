package com.eventi.left.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.member.mapper.MemberMapper;
import com.eventi.left.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired MemberMapper mapper;
	
	//아이디 중복확인
	@Override
	public int IdCheck(String inputId) {
		return mapper.IdCheck(inputId);
	}

}
