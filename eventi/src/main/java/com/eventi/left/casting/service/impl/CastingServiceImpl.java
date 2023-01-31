package com.eventi.left.casting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.admin.mapper.AdminMapper;
import com.eventi.left.casting.mapper.CastingMapper;
import com.eventi.left.casting.service.CastingService;
import com.eventi.left.casting.service.CastingVO;

@Service
public class CastingServiceImpl implements CastingService{
	@Autowired CastingMapper castingMapper;
	@Autowired AdminMapper adminMapper;
	
	//사회자별 전체조회
	@Override
	public List<CastingVO> mcCastingList(CastingVO castingVO) {
		return castingMapper.mcCastingList(castingVO);
	}

	//의뢰자별 전체조회
	@Override
	public List<CastingVO> clientCastingList(CastingVO castingVO) {
		return castingMapper.clientCastingList(castingVO);
	}
	
	//섭외 요청 추가
	@Override
	public int castingInsert(CastingVO castingVO) {
		return castingMapper.castingInsert(castingVO);
	}
	
	//진행현황업데이트
	@Override
	public int progressUpdate(CastingVO castingVO) {
		System.out.println("userId확인+++" + castingVO);
		
		if(castingVO.getUserId() != null) {
			adminMapper.banMember(castingVO.getUserId());
		}
		
		return castingMapper.progressUpdate(castingVO);
	}
	
	//환불금액찾기
	@Override
	public CastingVO refund(CastingVO castingVO) {
		return castingMapper.refund(castingVO);
	}
}
