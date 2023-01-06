package com.eventi.left.contest.mapper;

import java.util.List;

import com.eventi.left.contest.service.ContestVO;

public interface ContestMapper {
	// 공모전
		public List<ContestVO> contestList(ContestVO contestVO); // 전체조회

		public ContestVO getContest(ContestVO contestVO); // 1건조회

		public int insertContest(ContestVO contestVO); // 추가

		public int updateContest(ContestVO contestVO); // 수정

		public int deleteContest(String cNo); // 삭제
	}
