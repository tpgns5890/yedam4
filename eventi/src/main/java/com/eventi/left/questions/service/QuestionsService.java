package com.eventi.left.questions.service;

import java.util.List;

import org.springframework.stereotype.Service;

public interface QuestionsService {

	// 문의사항
	public List<QuestionsVO> questionsList(QuestionsVO vo); // 전체조회

	public int questionsInsert(QuestionsVO vo); // 등록

	public int questionsUpdate(QuestionsVO vo); // 수정

	public int questionsDelete(String qNo); // 삭제

}
