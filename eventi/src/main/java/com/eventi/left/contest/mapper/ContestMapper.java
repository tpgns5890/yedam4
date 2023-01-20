package com.eventi.left.contest.mapper;

import java.util.List;

import com.eventi.left.common.PagingVO;
import com.eventi.left.contest.service.ContestVO;

public interface ContestMapper {
		// 공모전
		public List<ContestVO> contestList(ContestVO contestVO); // 전체조회
		
		public List<ContestVO> myContestList(ContestVO ContestVO); // 작성한 공모전리스트 조회
		
		public int contestCount(ContestVO ContestVO); //공모전 등록건수(임시건수 제외)
		
		public int myContest(ContestVO ContestVO); //로그인된 회원의 공모전 등록건수조회.

		public ContestVO getContest(String cVo); // 1건조회
		
		public int selectUpdate(String cVo); // 상세조회 조회수 업데이트.
		
		public int selectLikes(ContestVO contestVO); //공모전 좋아요수 조회.

		public int insertContest(ContestVO contestVO); // 추가

		public int updateContest(ContestVO contestVO); // 제목,내용 수정
		
		public int updateExtension(ContestVO contestVO); //마감연장

		public int deleteContest(ContestVO contestVO); // 삭제
		
		public String getSequence(); //시퀀스 맥시멈번호 찾기.
		
		
}
