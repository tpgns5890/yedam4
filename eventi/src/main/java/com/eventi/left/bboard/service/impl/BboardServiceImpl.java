package com.eventi.left.bboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.bboard.mapper.BboardMapper;
import com.eventi.left.bboard.service.BboardService;
import com.eventi.left.bboard.service.BboardVO;

@Service
public class BboardServiceImpl implements BboardService{
	
	@Autowired BboardMapper bboardMapper;
	
	//전체 조회
	@Override
	public List<BboardVO> bboardList(BboardVO bboardVO) {
		return bboardMapper.bboardList(bboardVO);
	}
	
	//단건 조회
	@Override
	public BboardVO bboardSelect(BboardVO bboardVO) {
		return bboardMapper.bboardSelect(bboardVO);
	}


}
