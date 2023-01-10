package com.eventi.left.bboard.service;

import java.util.List;

public interface BboardService {
	//전체조회
	public List<BboardVO> bboardList(BboardVO bboardVO);
	
	//단건조회
	public BboardVO bboardSelect(BboardVO bboardVO);
}
