package com.eventi.left.promotion.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.reply.service.ReplyVO;

public interface PromotionService {
	
	//홍보게시물 전체조회
	public List<PromotionVO> proList(PromotionVO promotionVO);
	
	//게시물 상세조회
	public PromotionVO proDetail(PromotionVO promotionVO);
	
	//게시글 등록
	public int proInsert(PromotionVO promotionVO, MultipartFile uploadFile);
	
	//게시글 수정
	public int proUpdate(PromotionVO promotionVO);
	
	//게시글 삭제
	public int proDelete(PromotionVO promotionVO);
	
	//댓글 조회
	public List<ReplyVO> proReply(ReplyVO replyVO);
}
