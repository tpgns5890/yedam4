package com.eventi.left.questions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.questions.mapper.QuestionsMapper;
import com.eventi.left.questions.service.QuestionsService;
import com.eventi.left.questions.service.QuestionsVO;

@Service
public class QuestionsServiceImpl implements QuestionsService{

	@Autowired QuestionsMapper mapper;
	
	@Override
	public List<QuestionsVO> questionsList(QuestionsVO QuestionsVO) {
		return mapper.questionsList(QuestionsVO);
	}

	@Override
	public QuestionsVO getQuestions(QuestionsVO QuestionsVO) {
		return mapper.getQuestions(QuestionsVO);
	}

}
