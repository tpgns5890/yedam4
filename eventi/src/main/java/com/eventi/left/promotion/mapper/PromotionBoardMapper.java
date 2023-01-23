package com.eventi.left.promotion.mapper;

import java.util.List;

import com.eventi.left.promotion.service.PromotionVO;
import com.eventi.left.reply.service.ReplyVO;

public interface PromotionBoardMapper {
	
	//홍보게시판 전체조회
	public List<PromotionVO> proList(PromotionVO promotionVO);
	
	//게시물 조회수 
	public int seeUp(PromotionVO promotionVO);
	 
	//게시물 상세조회
	public PromotionVO proDetail(PromotionVO promotionVO); //vo로 들고오면 하나의 값 지정 안해도 됨
	
	//시퀀스 값 찾기
	public String getSeq();
	
	//게시글 등록
	public int proInsert(PromotionVO promotionVO);
	
	//게시글 수정
	public int proUpdate(PromotionVO promotionVO);
	
	//게시글 삭제
	public int proDelete(PromotionVO promotionVO);
	
	//댓글 조회
	public List<ReplyVO> proReply(ReplyVO replyVO);
	
	//게시글 개수
	public int count(PromotionVO promotionVO);
}
