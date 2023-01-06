package com.eventi.left.contest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.contest.mapper.WinnerMapper;
import com.eventi.left.contest.service.WinnerService;
import com.eventi.left.contest.service.WinnerVO;

@Service
public class WinnerServiceImpl implements WinnerService{

	@Autowired WinnerMapper mapper;	
	
	@Override
	public List<WinnerVO> winnerList(WinnerVO WinnerVO) {
		return mapper.winnerList(WinnerVO);
	}

	@Override
	public WinnerVO getWinner(WinnerVO WinnerVO) {
		return mapper.getWinner(WinnerVO);
	}

}
