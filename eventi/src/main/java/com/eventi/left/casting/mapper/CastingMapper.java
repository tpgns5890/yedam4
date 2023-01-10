package com.eventi.left.casting.mapper;

import java.util.List;

import com.eventi.left.casting.service.CastingVO;

public interface CastingMapper {
	List<CastingVO> castingList(CastingVO castingVO);
}
