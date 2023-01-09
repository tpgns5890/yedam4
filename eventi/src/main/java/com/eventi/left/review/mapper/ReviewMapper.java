package com.eventi.left.review.mapper;

import java.util.List;

import com.eventi.left.review.service.ReviewVO;

public interface ReviewMapper {
	
	//후기정보
	public List<ReviewVO> reviewList(ReviewVO ReviewVO); //전체조회 
	public ReviewVO getReview(ReviewVO ReviewVO); //1건조회 

}
