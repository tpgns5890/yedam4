package com.eventi.left.questions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.questions.mapper.QuestionsMapper;
import com.eventi.left.questions.service.QuestionsService;
import com.eventi.left.questions.service.QuestionsVO;

@Service
public class QuestionsServiceImpl implements QuestionsService{

	@Autowired QuestionsMapper mapper;
	
	@Override
	public List<QuestionsVO> questionsList(QuestionsVO vo, PagingVO paging) {
		paging.setTotalRecord(mapper.targetCount(vo));
		paging.setPageUnit(5);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
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
		return mapper.getSeq();
	}
	//회원아이디의 문의내역리스트 개수.
	@Override
	public int count(QuestionsVO vo) {
		String sessionId = (String) SessionUtil.getSession().getAttribute("sessionId");
		vo.setUserId(sessionId);
		return mapper.count(vo);
	}
	//공모전에 대한 나의문의내역리스트
	@Override
	public List<QuestionsVO> myQuestionsList(QuestionsVO vo, PagingVO paging) {
		System.out.println(vo);
		paging.setTotalRecord(mapper.targetCount(vo));  
		paging.setPageUnit(10);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return mapper.myQuestionsList(vo);
	}
	//문의+답변의 1건 리스트 조회
	@Override
	public List<QuestionsVO> getQuestionsList(String qNo) {
		return mapper.getQuestionsList(qNo);
	}


}
