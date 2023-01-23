package com.eventi.left.questions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.common.PagingVO;
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
	//문의등록 
	@Override
	public int questionsInsert(QuestionsVO vo) {
		return mapper.questionsInsert(vo);
	}

	@Override
	public int questionsUpdate(QuestionsVO vo) {
		return mapper.questionsUpdate(vo);
	}

	@Override
	public int questionsDelete(QuestionsVO questionsVO) {
		return mapper.questionsDelete(questionsVO);
	}
	//상세조회 
	@Override
	public QuestionsVO getQuestions(QuestionsVO vo) {
		return mapper.getQuestions(vo);
	}
	
	//Q&A 전체조회 
	@Override
	public List<QuestionsVO> qnaList(QuestionsVO vo, PagingVO paging) {
		paging.setTotalRecord(mapper.count(vo));
		paging.setPageUnit(5);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return mapper.qnaList(vo, paging);
	}
	//시퀀스 찾기
	@Override
	public String getSeq() {
		// TODO Auto-generated method stub
		return mapper.getSeq();
	}
	@Override
	public int count(QuestionsVO questionsVO) {
		// TODO Auto-generated method stub
		return 0;
	}


}
