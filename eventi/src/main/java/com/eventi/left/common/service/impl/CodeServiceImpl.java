package com.eventi.left.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.common.mapper.CodeMapper;
import com.eventi.left.common.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService{

	@Autowired CodeMapper mapper;
	
	@Override
	public String codeSelect(String code) {
		return mapper.codeSelect(code);
	}
	

}
