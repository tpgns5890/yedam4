package com.eventi.left.rent.mapper;

import java.util.List;

import com.eventi.left.rent.service.RentGdVO;

public interface RentMapper {
	//대여물품
	public List<RentGdVO> getRentGdList(RentGdVO rentGdVO); // 전체조회
	public RentGdVO getRentGd(RentGdVO rentGdVO); // 1건조회
	
	//대여내역
}
