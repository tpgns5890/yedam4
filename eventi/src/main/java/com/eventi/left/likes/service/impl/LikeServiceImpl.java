package com.eventi.left.likes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.likes.mapper.LikesMapper;
import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;

@Service
public class LikeServiceImpl implements LikesService {

	@Autowired LikesMapper mapper;
	
	//좋아요 전체 조회
	@Override
	public List<LikesVO> likeList(LikesVO LikesVO) {
		return mapper.likeList(LikesVO);
	}
	
	//좋아요 조회
	@Override
	public LikesVO getLike(LikesVO LikesVO) {
		return mapper.getLike(LikesVO);
	}
	
	
	//좋아요 개수
	@Override
	public int countLike(LikesVO LikesVO) {
		return mapper.countLike(LikesVO);
	}

	//좋아요 추가
	@Override
	public int likeInsert(LikesVO LikesVO) {
		return mapper.likeInsert(LikesVO);
	}
	
	//좋아요 취소
	@Override
	public int likeDelete(LikesVO LikesVO) {
		return mapper.likeDelete(LikesVO);
	}

}
