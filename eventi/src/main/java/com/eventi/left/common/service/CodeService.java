package com.eventi.left.common.service;

import java.util.List;

import com.eventi.left.common.CodeVO;

public interface CodeService {
	
	public String codeSelect(String code);
	
	//지역
	public List<CodeVO> getCountry();
	
	//행사타입
	public List<CodeVO> getType();
	
	//mc스타일
	public List<CodeVO> getMcStyle();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//수정(지은)
	//디자인 스타일
	public List<CodeVO> designStyle();
	
	//디자인 카테고리
	public List<CodeVO> getdesignCat();
	
	//렌트물품 카테고리
	public List<CodeVO> getRentCat();
}
