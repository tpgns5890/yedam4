package com.eventi.left.questions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.questions.mapper.QuestionsRepMapper;
import com.eventi.left.questions.service.QuestionsRepService;
import com.eventi.left.questions.service.QuestionsRepVO;

@Service
public class QuestionsRepServiceImpl implements QuestionsRepService {

	@Autowired QuestionsRepMapper mapper;
	
	@Override
	public List<QuestionsRepVO> questionsRepList(QuestionsRepVO QuestionsRepVO) {
		return mapper.questionsRepList(QuestionsRepVO);
	}

	@Override
	public QuestionsRepVO getQuestionsRep(QuestionsRepVO QuestionsRepVO) {
		return mapper.getQuestionsRep(QuestionsRepVO);
	}

}
