package com.eventi.left.rent.mapper;

import java.util.List;

import com.eventi.left.rent.service.RentGdVO;

public interface RentMapper {
	//대여물품
	public List<RentGdVO> getRentGdList(RentGdVO rentGdVO); // 전체조회
	public RentGdVO getRentGd(RentGdVO rentGdVO); // 1건조회
	
	//물품등록
	public int rentGdInsert(RentGdVO rentGdVO);
	
	//다음 물품 번호 찾기
	public String getSeq();
	
	//업체별 물품 개수
	public int count(RentGdVO rentGdVO);
}
