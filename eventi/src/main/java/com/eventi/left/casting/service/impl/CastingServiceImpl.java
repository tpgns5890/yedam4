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
	
	//섭외 전체 리스트
	@Override
	public List<CastingVO> castingList(CastingVO castingVO) {
		return mapper.castingList(castingVO);
	}
	
	//섭외 요청 추가
	@Override
	public int castingInsert(CastingVO castingVO) {
		return mapper.castingInsert(castingVO);
	}
	
}
