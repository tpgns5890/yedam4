package com.eventi.left.prtfl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.prtfl.mapper.McPrtfMapper;
import com.eventi.left.prtfl.service.McPrtflService;
import com.eventi.left.prtfl.service.McPrtflVO;

@Service
public class McPrtflServiceImpl implements McPrtflService{
	@Autowired McPrtfMapper mcPrtfMapper;
		
	@Override
	public List<McPrtflVO> mcList(McPrtflVO McPrtfVO) {
		return mcPrtfMapper.mcList(McPrtfVO);
	}

}
