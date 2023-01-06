package com.eventi.left.contest.service;

import java.util.List;

public interface ContestService {
	
	//공모전 
	public List<ContestVO> contestList(ContestVO ContestVO); //전체조회 
	public ContestVO getContest(ContestVO contestVO); //1건조회 
	public int insertContest(ContestVO contestVO); //추가
	public int updateContest(ContestVO contestVO); //수정
	public int deleteContest(String cNo); //삭제
}
