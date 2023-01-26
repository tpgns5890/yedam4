package com.eventi.left.casting.mapper;

import java.util.List;

import com.eventi.left.casting.service.CastingVO;

public interface CastingMapper {
	//전체조회
	public List<CastingVO> castingList(CastingVO castingVO);
	
	//섭외 요청 추가
	public int castingInsert(CastingVO castingVO);
}
