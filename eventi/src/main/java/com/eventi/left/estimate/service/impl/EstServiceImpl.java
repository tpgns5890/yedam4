package com.eventi.left.estimate.service.impl;

import java.util.List;
import java.util.Map;

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
	//견적서 단건조회
	@Override
	public EstVO getEst(String eno) {
		return estMapper.getEst(eno);
	}
	//견적서 전체조회
	@Override
	public List<EstVO> getEstList(EstVO estVO) {
		return estMapper.getEstList(estVO);
	}
	//제안서 견적서별 전체조회
	@Override
	public List<PropVO> getPropList(String eno) {
		return estMapper.getPropList(eno);
	}
	//업체 제안서 채택/후기수 조회 
	@Override
	public Map<String, String> getCount(String eno, String userId) {
		
		return estMapper.getCount(eno, userId);
	}
	
	// 제안서 등록
	@Override
	public PropVO insertProp(PropVO propVO) {
		return estMapper.insertProp(propVO);
	}
	
}
