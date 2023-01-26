package com.eventi.left.faq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.faq.mapper.FaqMapper;
import com.eventi.left.faq.service.FaqService;
import com.eventi.left.faq.service.FaqVO;

@Service
public class FaqServiceImpl implements FaqService{
	
	@Autowired
	FaqMapper faqmapper;
	
	//전체조회
	@Override
	public List<FaqVO> getFaqList(FaqVO faqVO) {
		return faqmapper.getFaqList(faqVO);
	}

}
