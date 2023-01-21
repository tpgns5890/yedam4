package com.eventi.left.likes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.common.SessionUtil;
import com.eventi.left.likes.mapper.LikesMapper;
import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;
import com.eventi.left.member.service.MemberVO;

@Service
public class LikeServiceImpl implements LikesService {

	@Autowired
	LikesMapper mapper;

	// 좋아요 회원 조회
	@Override
	public List<LikesVO> userlikeList(LikesVO LikesVO, String userId) {
		return mapper.userlikeList(LikesVO, userId);
	}

	// 좋아요 전체 조회
	@Override
	public List<LikesVO> likeList(LikesVO LikesVO) {
		return mapper.likeList(LikesVO);
	}

	// 좋아요 조회
	@Override
	public LikesVO getLike(LikesVO LikesVO) {
		return mapper.getLike(LikesVO);
	}
	
	
	//좋아요 개수
	@Override
	public int countLike(LikesVO LikesVO) {
		return mapper.countLike(LikesVO);
	}

	// 좋아요 추가
	@Override
	public int likeInsert(LikesVO LikesVO) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		LikesVO.setUserId(sessionId);
		
		//좋아요 누른 게시글이 이미등록된경우 리턴.
		int check = mapper.userlikecheck(LikesVO.getTargetNo(), LikesVO.getCategory(), sessionId);
		if(check == 0) {
			return mapper.likeInsert(LikesVO);
		}
		return 0;
	}

	// 좋아요 취소
	@Override
	public int likeDelete(LikesVO LikesVO) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		LikesVO.setUserId(sessionId);

		return mapper.likeDelete(LikesVO);
	}


}
