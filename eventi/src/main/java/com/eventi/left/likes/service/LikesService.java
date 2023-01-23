package com.eventi.left.likes.service;

import java.util.List;

import com.eventi.left.common.PagingVO;

public interface LikesService {

	// 좋아요
	public List<LikesVO> userlikeList(LikesVO vo,PagingVO paging) ; // 전체조회,세션아이디 조회.

	public List<LikesVO> likeList(LikesVO LikesVO); // 전체조회

	public LikesVO getLike(LikesVO LikesVO); // 1건조회

	public int likeInsert(LikesVO LikesVO); // 좋아요 입력

	public int likeDelete(LikesVO LikesVO); // 좋아요 취소

	// 좋아요 개수 조회
	public int countLike(LikesVO LikesVO);

}
