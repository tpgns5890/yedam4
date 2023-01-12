package com.eventi.left.design.mapper;

import java.util.List;

import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.design.service.DesignVO;

public interface DesignMapper {
	
	//디자인 전체 조회
	public List<DesignVO> designList(DesignVO vo);

	public int count(DesignVO vo);

	public List<DesignVO> contestDesignList(String cNo); //공모전 1건에 대한 리스트.

}
