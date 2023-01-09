package com.eventi.left.prtfl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.prtfl.mapper.BusiPrtflMapper;
import com.eventi.left.prtfl.service.BusiPrtflService;
import com.eventi.left.prtfl.service.BusiPrtflVO;

@Service
public class BusiPrtflServiceImpl implements BusiPrtflService{
	@Autowired BusiPrtflMapper mapper;
	
	@Override
	public List<BusiPrtflVO> busiList(BusiPrtflVO vo) {
		return mapper.busiList(vo);
	}

	
}
