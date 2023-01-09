package com.eventi.left.prtfl.service;

import java.util.List;

public interface McPrtflService {
	//사회자 전체 조회
	public List<McPrtflVO> mcAll(McPrtflVO McPrtflVO);
	
	//사회자 건별 조회
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO);
}
