package com.eventi.left.bboard.mapper;

import java.util.List;

import com.eventi.left.bboard.service.BboardVO;

public interface BboardMapper {
	//전체조회
	public List<BboardVO> bboardList(BboardVO bboardVO);
	
	//단건조회
	public BboardVO bboardSelect(BboardVO bboardVO);
	
	//좋아요 조회
	public List<BboardVO> bboardLike(BboardVO bboardVO);
	
	//조회수 +1
	public int inqUpdate(BboardVO bboardVO);
	
	//등록
	public int bboardInsert(BboardVO bboardVO);
	
	//수정
	public int bboardUpdate(BboardVO bboardVO);
	
	//삭제
	public int bboardDelete(BboardVO bboardVO);
}
