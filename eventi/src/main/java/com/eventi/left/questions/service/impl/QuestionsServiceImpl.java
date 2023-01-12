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
	public List<QuestionsVO> questionsList(QuestionsVO vo) {
		return mapper.questionsList(vo);
	}

	@Override
	public int questionsInsert(QuestionsVO vo) {
		return mapper.questionsInsert(vo);
	}

	@Override
	public int questionsUpdate(QuestionsVO vo) {
		return mapper.questionsUpdate(vo);
	}

	@Override
	public int questionsDelete(String qNo) {
		return mapper.questionsDelete(qNo);
	}


}
