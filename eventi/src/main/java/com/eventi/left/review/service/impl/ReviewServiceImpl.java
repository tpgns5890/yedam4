package com.eventi.left.review.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.review.mapper.ReviewMapper;
import com.eventi.left.review.service.ReviewService;
import com.eventi.left.review.service.ReviewVO;

@Service
public class ReviewServiceImpl  implements ReviewService{

	@Autowired ReviewMapper mapper;
	
	@Override
	public List<ReviewVO> reviewList(ReviewVO ReviewVO) {
		return mapper.reviewList(ReviewVO);
	}

	@Override
	public ReviewVO getReview(ReviewVO ReviewVO) {
		return mapper.getReview(ReviewVO);
	}

}
