package com.eventi.left.faq.mapper;

import java.util.List;

import com.eventi.left.faq.service.FaqVO;

public interface FaqMapper {
	//public FaqVO getFaq(FaqVO faqVO); 
	public List<FaqVO> getFaqList(FaqVO faqVO); 
	//public void faqInsert(FaqVO faqVO);
}