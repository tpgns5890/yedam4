package com.eventi.left.contest.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.service.FilesVO;

public interface ContestService {

	public List<ContestVO> contestList(ContestVO ContestVO, PagingVO paging); // 공모전 전체조회(페이징)

	public List<ContestVO> myContestList(ContestVO ContestVO, PagingVO paging); // 작성한 공모전리스트 조회

	public ContestVO getContest(ContestVO contestVO); // 공모전 1건에 대한 등록조회

	public int updateContest(ContestVO contestVO); // 수정

	public int deleteContest(ContestVO contestVO); // 삭제

	public String getSequence(); // 시퀀스 맥시멈번호 찾기.

	// 공모전,다중파일,우승상금 등록
	public int insertContest(ContestVO vo, FilesVO files, List<MultipartFile> uploadFile, WinnerVO wvo); // 추가

	// 공모전 마감일수
	public Map<ContestVO, Integer> getDday(ContestVO vo);

}
