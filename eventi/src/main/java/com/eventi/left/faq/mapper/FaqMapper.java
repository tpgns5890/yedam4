package com.eventi.left.faq.mapper;

import java.util.List;

import com.eventi.left.faq.service.FaqVO;

public interface FaqMapper {
	
	//전체조회 
	public List<FaqVO> getFaqList(FaqVO faqVO); 
}