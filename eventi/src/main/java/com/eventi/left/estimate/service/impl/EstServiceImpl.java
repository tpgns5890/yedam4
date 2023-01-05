package com.eventi.left.estimate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.estimate.mapper.EstMapper;
import com.eventi.left.estimate.service.EstService;
import com.eventi.left.estimate.service.EstVO;
import com.eventi.left.estimate.service.PropVO;

@Service
public class EstServiceImpl implements EstService {

	@Autowired
	EstMapper estMapper;

	@Override
	public EstVO getEst(EstVO estVO) {
		return estMapper.getEst(estVO);
	}

	@Override
	public List<EstVO> getEstList(EstVO estVO) {
		return estMapper.getEstList(estVO);
	}

	@Override
	public PropVO getPropList(PropVO propVO) {
		return estMapper.getPropList(propVO);
	}

	@Override
	public PropVO insertProp(PropVO propVO) {
		return estMapper.insertProp(propVO);
	}
}
