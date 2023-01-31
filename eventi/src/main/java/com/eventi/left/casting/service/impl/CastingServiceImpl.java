package com.eventi.left.casting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.casting.mapper.CastingMapper;
import com.eventi.left.casting.service.CastingService;
import com.eventi.left.casting.service.CastingVO;

@Service
public class CastingServiceImpl implements CastingService{
	@Autowired CastingMapper mapper;
	
	//사회자별 전체조회
	@Override
	public List<CastingVO> mcCastingList(CastingVO castingVO) {
		return mapper.mcCastingList(castingVO);
	}

	//의뢰자별 전체조회
	@Override
	public List<CastingVO> clientCastingList(CastingVO castingVO) {
		return mapper.clientCastingList(castingVO);
	}
	
	//섭외 요청 추가
	@Override
	public int castingInsert(CastingVO castingVO) {
		return mapper.castingInsert(castingVO);
	}
	
	//진행현황업데이트
	@Override
	public int progressUpdate(CastingVO castingVO) {
		return mapper.progressUpdate(castingVO);
	}
}
