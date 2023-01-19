package com.eventi.left.contest.service;

import java.util.List;

public interface WinnerService {
	
	//상금 및 우승자 정보
	public List<WinnerVO> winnerList(String cNo); //공모전 1건에 대한 우승정보 전체조회 
	public WinnerVO getWinner(WinnerVO WinnerVO); //공모전 1건에 대한 우승정보 1건조회
	public int updateWinner(WinnerVO WinnerVO); //등록된 우승상금과 공모전에 회원정보와 등수 업데이트
	public int deleteWinner(String wNo); //삭제
	
	
	
}
