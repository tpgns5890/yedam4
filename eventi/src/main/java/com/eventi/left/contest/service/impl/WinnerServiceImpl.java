package com.eventi.left.contest.service.impl;

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

	// 공고 마감후 우승자등록(업데이트)
	@Override
	public int updateWinner(WinnerVO vo) {

		List<WinnerVO> winners = mapper.winnerList(vo.getCoNo());
		String[] dgnNo;
		String[] userIdArr;
		int r = 0;

		// 공모전 우승입력개수 가져와서 사이즈만큼 반복문
		// str 값에 입력한 디자이너번호를 받고
		// 1등부터 순서대로 update
		for (int i = 0; i < winners.size(); i++) {
			dgnNo = vo.getDgnNo();
			userIdArr = vo.getUserIdArr();
			winners.get(i).setCoNo(vo.getCoNo());
			winners.get(i).setdNo(dgnNo[i]);
			winners.get(i).setGrade(i + 1);
			winners.get(i).setUserId(userIdArr[i]);
			r += mapper.updateWinner(winners.get(i));
		}
		return r;
	}

	@Override
	public int deleteWinner(String wNo) {
		return mapper.deleteWinner(wNo);
	}

	@Override
	public List<WinnerVO> winnerSelect(String cNo) {
		return mapper.winnerSelect(cNo);
	}

}
