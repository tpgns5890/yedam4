package com.eventi.left.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.common.CodeVO;
import com.eventi.left.common.mapper.CodeMapper;
import com.eventi.left.common.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService{

	@Autowired CodeMapper CodeMapper;
	
	@Override
	public String codeSelect(String code) {
		return CodeMapper.codeSelect(code);
	}

	//지역
	@Override
	public List<CodeVO> getCountry() {
		return CodeMapper.getCountry();
	}
	
	//행사타입
	@Override
	public List<CodeVO> getType() {
		return CodeMapper.getType();
	}

	//사회자스타일
	@Override
	public List<CodeVO> getMcStyle() {
		return CodeMapper.getMcStyle();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//지은(수정)
	//디자인 스타일
	@Override
	public List<CodeVO> designStyle() {
		return CodeMapper.designStyle();
	}

	//디자인 카테고리
	@Override
	public List<CodeVO> getdesignCat() {
		return CodeMapper.getdesignCat();
	}
	
	//렌탈물품 카테고리
	@Override
	public List<CodeVO> getRentCat() {
		return CodeMapper.getRentCat();
	}
	

}
