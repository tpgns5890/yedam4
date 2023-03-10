package com.eventi.left.prtfl.mapper;

import java.util.List;

import com.eventi.left.prtfl.service.BusiPrtflVO;

public interface BusiPrtflMapper {
	//전체조회
	public List<BusiPrtflVO> busiList(BusiPrtflVO busiPrtflVO);
	
	//전체 건수
	public int count(BusiPrtflVO busiPrtflVO);
	
	//단건조회
	public BusiPrtflVO busiSelect(BusiPrtflVO busiPrtflVO);
	
	//업체 등록 여부
	public int checkBusi(BusiPrtflVO busiPrtflVO);
	
	//등록
	public int busiInsert(BusiPrtflVO busiPrtflVO);
}
