package com.eventi.left.common.mapper;

import java.util.List;

import com.eventi.left.common.CodeVO;

public interface CodeMapper {
	
	public String codeSelect(String code);

	//지역
	public List<CodeVO> getCountry();
		
	//행사타입
	public List<CodeVO> getType();
		
	//mc스타일
	public List<CodeVO> getMcStyle();
	
	//공모전 스타일
	public List<CodeVO> getContestStyle();
}
