package com.eventi.left.contest.service;

import java.util.List;

public interface WinnerService {
	
	//상금 및 우승자 정보
	public List<WinnerVO> winnerList(WinnerVO WinnerVO); //전체조회 
	public WinnerVO getWinner(WinnerVO WinnerVO); //1건조회 

}
