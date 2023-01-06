package com.eventi.left.contest.mapper;

import java.util.List;

import com.eventi.left.contest.service.WinnerVO;

public interface WinnerMapper {
	
	//상금 및 우승자 정보
		public List<WinnerVO> winnerList(WinnerVO WinnerVO); //전체조회 
		public WinnerVO getWinner(WinnerVO WinnerVO); //1건조회 

}
