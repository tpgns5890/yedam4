package com.eventi.left.bboard.mapper;

import java.util.List;

import com.eventi.left.bboard.service.BboardVO;

public interface BboardMapper {
	public List<BboardVO> bboardList(BboardVO bboardVO);
}
