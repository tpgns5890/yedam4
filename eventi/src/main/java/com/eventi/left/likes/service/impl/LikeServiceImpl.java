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
	
	@Override
	public List<LikesVO> likeList(LikesVO LikesVO) {
		return mapper.likeList(LikesVO);
	}

	@Override
	public LikesVO getLike(LikesVO LikesVO) {
		return mapper.getLike(LikesVO);
	}

}
