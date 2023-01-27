package com.eventi.left.casting.service;

import java.util.List;

public interface CastingService {
	//전체조회
	public List<CastingVO> castingList(CastingVO castingVO);
	
	//섭외 요청 추가
	public int castingInsert(CastingVO castingVO);
}
