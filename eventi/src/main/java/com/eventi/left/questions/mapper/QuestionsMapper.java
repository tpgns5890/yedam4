package com.eventi.left.questions.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eventi.left.common.PagingVO;
import com.eventi.left.questions.service.QuestionsVO;

public interface QuestionsMapper {

	// 문의사항
	public List<QuestionsVO> questionsList(QuestionsVO vo); // 공모전 문의사항+답변
	
	public List<QuestionsVO> myQuestionsList(QuestionsVO vo); // 나의 공모전 문의사항리스트

	public QuestionsVO getQuestions(QuestionsVO vo); // 1건 조회

	public int questionsInsert(QuestionsVO vo); // 등록

	public int questionsUpdate(QuestionsVO vo); // 수정

	public int questionsDelete(QuestionsVO vo); // 삭제

	// Q&A 전체조회
	public List<QuestionsVO> qnaList(QuestionsVO vo, PagingVO paging);

	// 시퀀스 값 찾기
	public String getSeq();

	// 회원아이디의 문의내역리스트 개수.
	public int count(QuestionsVO questionsVO);

	// 게시글에 문의내역리스트 개수
	public int targetCount(QuestionsVO questionsVO);
}
