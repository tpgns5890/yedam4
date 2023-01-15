package com.eventi.left.design.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.common.Paging;
import com.eventi.left.design.mapper.DesignMapper;
import com.eventi.left.design.service.DesignService;
import com.eventi.left.design.service.DesignVO;

@Service
public class DesignServiceImpl implements DesignService{
	@Autowired DesignMapper mapper;
	
	@Override
	public List<DesignVO> designList(DesignVO vo, Paging paging) {
		//페이지 처리
		paging.setTotalRecord(mapper.count(vo));
		//한페이지에 나타날 수
		paging.setPageUnit(4);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return mapper.designList(vo);
	}

	@Override
	public List<DesignVO> contestDesignList(String cNo) {
		return mapper.contestDesignList(cNo);
	}
}
