package com.eventi.left.promotion.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface PromotionService {
	
	//홍보게시물 전체조회
	public List<PromotionVO> proList(PromotionVO promotionVO);
	
	/*
	 * //게시물 조회수 public int seeUp(PromotionVO promotionVO);
	 */
	
	//게시물 상세조회
	public PromotionVO proDetail(PromotionVO promotionVO);
	
	//게시글 등록
	public int proInsert(PromotionVO promotionVO, MultipartFile uploadFile);
}
