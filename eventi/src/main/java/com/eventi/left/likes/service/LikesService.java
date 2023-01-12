package com.eventi.left.likes.service;

import java.util.List;

public interface LikesService {
	
	// 좋아요
	public List<LikesVO> likeList(LikesVO LikesVO); // 전체조회

	public LikesVO getLike(LikesVO LikesVO); // 1건조회
	
	//좋아요 입력
	public int likeInsert(LikesVO LikesVO);
	
	//좋아요 취소
	public int likeDelete(LikesVO LikesVO);
}
