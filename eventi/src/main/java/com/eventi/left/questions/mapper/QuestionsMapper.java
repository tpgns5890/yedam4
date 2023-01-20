package com.eventi.left.questions.mapper;

import java.util.List;

import com.eventi.left.questions.service.QuestionsVO;

public interface QuestionsMapper {

		//문의사항
		public List<QuestionsVO> questionsList(QuestionsVO vo); //전체조회 
		
		public QuestionsVO getQuestions(QuestionsVO vo); // 1건 조회
		
		public int questionsInsert(QuestionsVO vo); //등록
		
		public int questionsUpdate(QuestionsVO vo); //수정

		public int questionsDelete(QuestionsVO vo); //삭제
		
		//Q&A 전체조회
		public List<QuestionsVO> qnaList(QuestionsVO vo); 

}
