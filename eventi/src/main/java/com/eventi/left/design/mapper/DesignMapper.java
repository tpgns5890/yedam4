package com.eventi.left.design.mapper;

import java.util.List;

import com.eventi.left.contest.service.ContestVO;

public interface DesignMapper {

	// 공모전
	public List<ContestVO> contestList(); // 전체조회

	public ContestVO getContest(ContestVO contestVO); // 1건조회

	public int insertContest(ContestVO contestVO); // 추가

	public int updateContest(ContestVO contestVO); // 수정

	public int deleteContest(ContestVO contestVO); // 삭제

}
