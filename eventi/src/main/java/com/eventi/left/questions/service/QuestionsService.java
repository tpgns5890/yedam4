package com.eventi.left.questions.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eventi.left.common.PagingVO;
import com.eventi.left.promotion.service.PromotionVO;

public interface QuestionsService {

	// 문의사항
	public List<QuestionsVO> questionsList(QuestionsVO vo, PagingVO paging); // 게시글1건에 대한 전체조회
	
	public List<QuestionsVO> myQuestionsList(QuestionsVO vo, PagingVO paging); // 나의 공모전 문의사항리스트
	
	public QuestionsVO getQuestions(QuestionsVO vo); // 1건 조회

	public int questionsInsert(QuestionsVO vo); // 등록

	public int questionsUpdate(QuestionsVO vo); // 수정

	public int questionsDelete(QuestionsVO questionsVO); // 삭제

	//Q&A 전체조회
	public List<QuestionsVO> qnaList(QuestionsVO vo, PagingVO paging);
	
	//시퀀스 값 찾기
	public String getSeq();
	
	//게시글 개수
	public int count(QuestionsVO questionsVO);

}
