package com.eventi.left.contest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.common.Paging;
import com.eventi.left.contest.mapper.ContestMapper;
import com.eventi.left.contest.mapper.WinnerMapper;
import com.eventi.left.contest.service.ContestService;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerVO;

@Service
public class ContestServiceImpl implements ContestService{

	@Autowired ContestMapper mapper;
	@Autowired WinnerMapper wMapper;
	
	@Override
	public List<ContestVO> contestList(ContestVO vo, Paging paging) {
		paging.setTotalRecord(mapper.contestCount(vo));
		paging.setPageUnit(12); //12개 출력 (default 10)
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return mapper.contestList(vo);
		
	}

	@Override
	public ContestVO getContest(ContestVO vo) {
		mapper.selectUpdate(vo);
		return mapper.getContest(vo);
	}

	@Override
	public int insertContest(ContestVO vo) {
		return mapper.insertContest(vo);
	}

	@Override
	public int updateContest(ContestVO vo) {
		return mapper.updateContest(vo);
	}


	@Override
	public int deleteContest(String cNo) {
		return mapper.deleteContest(cNo);
	}

	@Override
	public String getSequence() {
		return mapper.getSequence();
	}

}
