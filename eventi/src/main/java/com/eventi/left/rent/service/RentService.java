package com.eventi.left.rent.service;

import java.util.List;

public interface RentService {
	//대여물품
	public List<RentGdVO> getRentGdList(RentGdVO rentGdVO); // 전체조회
	public RentGdVO getRentGd(RentGdVO rentGdVO); // 1건조회
	
}
