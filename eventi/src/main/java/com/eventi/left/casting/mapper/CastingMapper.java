package com.eventi.left.casting.mapper;

import java.util.List;

import com.eventi.left.casting.service.CastingVO;

public interface CastingMapper {
	//사회자별 전체조회
	public List<CastingVO> mcCastingList(CastingVO castingVO);
		
	//의뢰자별 전체조회
	public List<CastingVO> clientCastingList(CastingVO castingVO);
	
	//섭외 요청 추가
	public int castingInsert(CastingVO castingVO);
	
	//진행현황업데이트
	public int progressUpdate(CastingVO castingVO);
	
	//환불금액찾기
	public CastingVO refund(CastingVO castingVO);
}
