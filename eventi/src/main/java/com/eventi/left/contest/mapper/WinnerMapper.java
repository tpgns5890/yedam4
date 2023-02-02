package com.eventi.left.contest.mapper;

import java.util.List;

import com.eventi.left.contest.service.WinnerVO;

public interface WinnerMapper {

	// 상금 및 우승자 정보
	public List<WinnerVO> winnerList(String cNo); // 공모전 1건에 대한 우승정보 전체조회

	public List<WinnerVO> winnerSelect(String cNo); // 공모전 1건에 대한 우승결과(등수,금액,대표디자인) 전체조회

	public WinnerVO getWinner(WinnerVO WinnerVO); // 공모전 1건에 대한 우승정보 1건조회

	public int insertWinner(WinnerVO WinnerVO); // 추가

	public int updateWinner(WinnerVO WinnerVO); // 우승자업데이트

	public int updateSaveWinner(WinnerVO WinnerVO); // 임시저장 업데이트

	public int deleteWinner(String wNo); // 삭제

	public List<WinnerVO> updateGetWinner(WinnerVO WinnerVO); // 등록된 공고번호 체크후 객체얻어서 업데이트.

}
