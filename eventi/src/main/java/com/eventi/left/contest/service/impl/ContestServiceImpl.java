package com.eventi.left.contest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.eventi.left.contest.mapper.ContestMapper;
import com.eventi.left.contest.service.ContestService;
import com.eventi.left.contest.service.ContestVO;

public class ContestServiceImpl implements ContestService{

	@Autowired ContestMapper mapper;
	
	@Override
	public List<ContestVO> contestList(ContestVO contestVO) {
		return mapper.contestList(contestVO);
	}

	@Override
	public ContestVO getContest(ContestVO contestVO) {
		return mapper.getContest(contestVO);
	}

	@Override
	public int insertContest(ContestVO contestVO) {
		return mapper.insertContest(contestVO);
	}

	@Override
	public int updateContest(ContestVO contestVO) {
		return mapper.updateContest(contestVO);
	}


	@Override
	public int deleteContest(String cNo) {
		return mapper.deleteContest(cNo);
	}

}
