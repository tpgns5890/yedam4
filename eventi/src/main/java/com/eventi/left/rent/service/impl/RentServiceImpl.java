package com.eventi.left.rent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.eventi.left.estimate.mapper.EstMapper;
import com.eventi.left.rent.mapper.RentMapper;
import com.eventi.left.rent.service.RentGdVO;
import com.eventi.left.rent.service.RentService;

public class RentServiceImpl implements RentService{

	@Autowired
	RentMapper rentGdMapper;
	
	@Override
	public List<RentGdVO> getRentGdList(RentGdVO rentGdVO) { //대여물품 전체조회
		return rentGdMapper.getRentGdList(rentGdVO);
	}

	@Override
	public RentGdVO getRentGd(RentGdVO rentGdVO) { //대여물품 한건조회
		return rentGdMapper.getRentGd(rentGdVO);
	}

}
