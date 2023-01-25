package com.eventi.left.review.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.review.mapper.ReviewMapper;
import com.eventi.left.review.service.ReviewService;
import com.eventi.left.review.service.ReviewVO;

@Service
public class ReviewServiceImpl  implements ReviewService{

	@Autowired ReviewMapper reviewMapper;
	
	//내가 작성한 후기 전체 조회
	@Override
	public List<ReviewVO> reviewList(ReviewVO reviewVO) {
		return reviewMapper.reviewList(reviewVO);
	}

	//타겟별 후기 조회
	@Override
	public List<ReviewVO> getReview(ReviewVO reviewVO) {
		return reviewMapper.getReview(reviewVO);
	}
	
	//별점 평균, 후기 건수
	@Override
	public ReviewVO getReviewAvg(ReviewVO reviewVO) {
		return reviewMapper.getReviewAvg(reviewVO);
	}
	
	//후기 등록
	@Override
	public int reviewInsert(ReviewVO reviewVO) {
		return reviewMapper.reviewInsert(reviewVO);
	}

	//후기 수정
	@Override
	public int reviewUpdate(ReviewVO reviewVO) {
		return reviewMapper.reviewUpdate(reviewVO);
	}

	//후기 삭제
	@Override
	public int reviewDelete(ReviewVO reviewVO) {
		return reviewMapper.reviewDelete(reviewVO);
	}
}
