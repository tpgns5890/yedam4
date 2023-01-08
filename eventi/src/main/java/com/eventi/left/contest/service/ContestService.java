package com.eventi.left.contest.service;

import java.util.List;

import com.eventi.left.common.Paging;

public interface ContestService {
	
	// 공모전 
	public List<ContestVO> contestList(ContestVO ContestVO, Paging paging); //전체조회(페이징)
	public ContestVO getContest(ContestVO contestVO); //1건조회 
	public int insertContest(ContestVO contestVO); //추가
	public int updateContest(ContestVO contestVO); //수정
	public int deleteContest(String cNo); //삭제
}
