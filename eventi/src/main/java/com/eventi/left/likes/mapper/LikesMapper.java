package com.eventi.left.likes.mapper;

import java.util.List;

import com.eventi.left.likes.service.LikesVO;

public interface LikesMapper {
	// 좋아요
	public List<LikesVO> likeList(LikesVO LikesVO); // 전체조회

	public LikesVO getLike(LikesVO LikesVO); // 1건조회
}
