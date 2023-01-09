package com.eventi.left.review.service;

import java.util.List;

public interface ReviewService {
	
	//후기정보
		public List<ReviewVO> reviewList(ReviewVO ReviewVO); //전체조회 
		public ReviewVO getReview(ReviewVO ReviewVO); //1건조회 

}
