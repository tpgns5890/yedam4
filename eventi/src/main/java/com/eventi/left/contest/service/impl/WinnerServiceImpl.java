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
	public List<WinnerVO> winnerList(String cNo) {
		return mapper.winnerList(cNo);
	}

	@Override
	public WinnerVO getWinner(WinnerVO WinnerVO) {
		return mapper.getWinner(WinnerVO);
	}

	@Override
	public int insertWinner(WinnerVO WinnerVO) {
		return mapper.insertWinner(WinnerVO);
	}

	@Override
	public int updateWinner(WinnerVO WinnerVO) {
		return mapper.updateWinner(WinnerVO);
	}

	@Override
	public int deleteWinner(String wNo) {
		return mapper.deleteWinner(wNo);
	}

}
