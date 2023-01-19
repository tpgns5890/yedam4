package com.eventi.left.contest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.contest.mapper.WinnerMapper;
import com.eventi.left.contest.service.WinnerService;
import com.eventi.left.contest.service.WinnerVO;

@Service
public class WinnerServiceImpl implements WinnerService {

	@Autowired
	WinnerMapper mapper;

	@Override
	public List<WinnerVO> winnerList(String cNo) {
		return mapper.winnerList(cNo);
	}

	@Override
	public WinnerVO getWinner(WinnerVO WinnerVO) {
		return mapper.getWinner(WinnerVO);
	}

	@Override
	public int updateWinner(WinnerVO vo) {

		// 등록된 공고번호가 없다면
		List<WinnerVO> list = new ArrayList<>();
		List<WinnerVO> winners = mapper.updateGetWinner(vo);
		WinnerVO winner = null;
		String[] str;
		str = vo.getDgnNo();
		for (int i = 0; i < winners.size(); i++) {
			winner = winners.get(i);
		}
		for (int i = 1; i < str.length; i++) {
			winner.setdNo(str[i]);
			winner.setGrade(i);
			mapper.updateWinner(winner);
			System.out.println(str[i]);
			System.out.println(winner.getdNo());
		}
		return 0;
	}

	@Override
	public int deleteWinner(String wNo) {
		return mapper.deleteWinner(wNo);
	}

}
