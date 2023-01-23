package com.eventi.left.notice.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;

public interface NoticeService {
	
	//게시글 전체조회
	public List<NoticeVO> noticeList(NoticeVO noticeVO, PagingVO paging);
	
	//시퀀스 값 찾기
	public String getSeq();
	
	//게시글 등록
	public int nocInsert(NoticeVO noticeVO, MultipartFile uploadFile);
	
	//게시물 상세조회
	public NoticeVO nocDetail(NoticeVO noticeVO);
	
	//게시글 수정
	public int nocUpdate(NoticeVO noticeVO);
	
	//게시글 삭제
	public int nocDelete(NoticeVO noticeVO);
	
	//게시글 개수
	public int count(NoticeVO noticeVO);
	
	//게시물 조회수 
	public int seeUp(NoticeVO noticeVO);
}
