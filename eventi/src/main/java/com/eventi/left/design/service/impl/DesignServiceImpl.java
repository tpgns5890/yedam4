package com.eventi.left.design.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.design.mapper.DesignMapper;
import com.eventi.left.design.service.DesignService;
import com.eventi.left.design.service.DesignVO;

@Service
public class DesignServiceImpl implements DesignService{
	@Autowired DesignMapper mapper;
	
	@Override
	public List<DesignVO> designList(DesignVO vo) {
		return mapper.designList(vo);
	}
}
