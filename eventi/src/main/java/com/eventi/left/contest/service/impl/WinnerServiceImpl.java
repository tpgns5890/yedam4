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

	@Override
	public int updateWinner(WinnerVO vo) {

		// 등록된 공고번호가 없다면
		List<WinnerVO> winners = mapper.winnerList(vo.getCoNo());
		System.out.println(winners);
		String[] dgnNo;
		String[] userIdArr;
		
		// 공모전 우승입력개수 가져와서 사이즈만큼 반복문
		// str 값에 입력한 디자이너번호를 받고
		// 1등부터 순서대로 update
		for (int i = 0; i < winners.size(); i++) {
			dgnNo = vo.getDgnNo();
			userIdArr = vo.getUserIdArr();
			winners.get(i).setCoNo(vo.getCoNo());
			winners.get(i).setdNo(dgnNo[i]);
			winners.get(i).setGrade(i+1);
			winners.get(i).setUserId(userIdArr[i]);
			mapper.updateWinner(winners.get(i));
		}
		return 0;
	}

	@Override
	public int deleteWinner(String wNo) {
		return mapper.deleteWinner(wNo);
	}

}
