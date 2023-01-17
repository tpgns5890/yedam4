package com.eventi.left.design.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.common.PagingVO;
import com.eventi.left.design.mapper.DesignMapper;
import com.eventi.left.design.service.DesignService;
import com.eventi.left.design.service.DesignVO;

@Service
public class DesignServiceImpl implements DesignService {
	@Autowired
	DesignMapper mapper;

	@Override
	public List<DesignVO> designList(DesignVO vo, PagingVO paging) {
		paging.setTotalRecord(mapper.count(vo));
		paging.setPageUnit(4);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return mapper.designList(vo);
	}

	@Override
	public List<DesignVO> contestDesignList(String cNo) {
		return mapper.contestDesignList(cNo);
	}

	@Override
	public int insert(DesignVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int update(DesignVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int delete(String dNo) {
		return mapper.delete(dNo);
	}

	@Override
	public String getSequence() {
		return mapper.getSequence();
	}
}
