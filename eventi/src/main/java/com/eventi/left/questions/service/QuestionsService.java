package com.eventi.left.questions.service;

import java.util.List;

import com.eventi.left.contest.service.WinnerVO;

public interface QuestionsService {

	// 문의사항
	public List<QuestionsVO> questionsList(QuestionsVO QuestionsVO); // 전체조회

	public QuestionsVO getQuestions(QuestionsVO QuestionsVO); // 1건조회

}
