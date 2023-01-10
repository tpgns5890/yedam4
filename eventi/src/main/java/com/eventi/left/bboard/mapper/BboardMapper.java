package com.eventi.left.bboard.mapper;

import java.util.List;

import com.eventi.left.bboard.service.BboardVO;

public interface BboardMapper {
	//전체조회
	public List<BboardVO> bboardList(BboardVO bboardVO);
	
	//단건조회
	public BboardVO bboardSelect(BboardVO bboardVO);
}
