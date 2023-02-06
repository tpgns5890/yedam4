package com.eventi.left.faq.mapper;

import java.util.List;

import com.eventi.left.faq.service.FaqVO;

public interface FaqMapper {
	
	//전체조회 
	public List<FaqVO> getFaqList(FaqVO faqVO);
	
	//등록
	public int faqInsert(FaqVO faqVO);
	
	//시퀀스 값 찾기
	public String getSeq();
	
	//삭제
	public int faqDelete(FaqVO faqVO);
}