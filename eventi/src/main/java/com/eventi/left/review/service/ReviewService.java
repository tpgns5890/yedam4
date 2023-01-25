package com.eventi.left.review.service;

import java.util.List;

public interface ReviewService {
	//내가 작성한 후기 전체 조회
	public List<ReviewVO> reviewList(ReviewVO reviewVO);
	
	//타겟별 후기 조회
	public List<ReviewVO> getReview(ReviewVO reviewVO);
	
	//별점 평균, 후기 건수
	public ReviewVO getReviewAvg(ReviewVO reviewVO);
	
	//후기 등록
	public int reviewInsert(ReviewVO reviewVO);
	
	//후기 수정
	public int reviewUpdate (ReviewVO reviewVO);
	
	//후기 삭제
	public int reviewDelete (ReviewVO reviewVO);
}
