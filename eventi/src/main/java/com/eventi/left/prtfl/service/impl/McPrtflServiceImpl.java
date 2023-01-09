package com.eventi.left.prtfl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.prtfl.mapper.McPrtflMapper;
import com.eventi.left.prtfl.service.McPrtflService;
import com.eventi.left.prtfl.service.McPrtflVO;

@Service
public class McPrtflServiceImpl implements McPrtflService{
	@Autowired McPrtflMapper mapper;
		
	//전체조회
	@Override
	public List<McPrtflVO> mcAll(McPrtflVO McPrtfVO) {
		return mapper.mcAll(McPrtfVO);
	}
	
	//건별조회
	@Override
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO) {
		return mapper.mcSelect(mcPrtflVO);
	}
	
	//댓글 전체조회

}
