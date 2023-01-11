package com.eventi.left.contest.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.Paging;
import com.eventi.left.files.service.FilesVO;

public interface ContestService {
	
	// 공모전 
	public List<ContestVO> contestList(ContestVO ContestVO, Paging paging); //전체조회(페이징)
	public ContestVO getContest(ContestVO contestVO); //1건조회 
	
	public int updateContest(ContestVO contestVO); //수정
	public int deleteContest(String cNo); //삭제
	public String getSequence(); //시퀀스 맥시멈번호 찾기.
	
	//공모전,다중파일,우승상금 등록
	public int insertContest(ContestVO vo, FilesVO files, List<MultipartFile> uploadFile, WinnerVO wvo); //추가
}
