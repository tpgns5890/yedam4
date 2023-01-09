package com.eventi.left.prtfl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.prtfl.mapper.DgnerPrtflMapper;
import com.eventi.left.prtfl.service.DgnerPrtflService;
import com.eventi.left.prtfl.service.DgnerPrtflVO;

@Service
public class DgnerPrtflServiceImpl implements DgnerPrtflService{
	@Autowired DgnerPrtflMapper mapper;
	
	@Override
	public DgnerPrtflVO dgnerSelect(DgnerPrtflVO vo) {
		return mapper.dgnerSelect(vo);
	}

}
