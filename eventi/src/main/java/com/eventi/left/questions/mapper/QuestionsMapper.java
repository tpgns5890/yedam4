package com.eventi.left.questions.mapper;

import java.util.List;

import com.eventi.left.questions.service.QuestionsVO;

public interface QuestionsMapper {

	//문의사항
		public List<QuestionsVO> questionsList(QuestionsVO vo); //전체조회 
		public QuestionsVO getQuestions(QuestionsVO QuestionsVO); //1건조회 

}
