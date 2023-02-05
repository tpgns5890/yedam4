package com.eventi.left.contest.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;

public interface ContestService {

	public List<ContestVO> contestList(ContestVO ContestVO, PagingVO paging); // 공모전 전체조회(페이징)

	public List<ContestVO> myContestList(ContestVO ContestVO, PagingVO paging); // 작성한 공모전리스트 조회

	public ContestVO getContest(String cVo); // 공모전 1건에 대한 등록조회
	
	public ContestVO saveGetContest(ContestVO contestVO); // 임시저장 1건,우승자1~3등 조회

	public int updateContest(ContestVO vo, MultipartFile[] uploadFile); // 수정
	
	public int saveUpdateContest(ContestVO vo, MultipartFile[] uploadFile, WinnerVO wvo); //임시저장 불러오기 수정.

	public int deleteContest(ContestVO contestVO); // 삭제

	public String getSequence(); // 시퀀스 맥시멈번호 찾기.

	public int insertContest(ContestVO vo, WinnerVO wvo, MultipartFile[] uploadFile); // 공모전,우승상금 등록
	
	public List<ContestVO> cSave(ContestVO vo); //임시저장 리스트.
	
}
