package com.eventi.left.job.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface JobService {
	
	public List<JobBoardVO> getJobList(JobBoardVO jobBoardVO); //전체조회
	
	public JobBoardVO getJob(JobBoardVO jobBoardVO); //게시물 상세조회
	
	public int getJobUpdate(JobBoardVO jobBoardVO); //게시글수정
	
	public int jobInsert(JobBoardVO jobBoardVO, MultipartFile uploadFile); //게시글 등록
	
	public int jobDelete(JobBoardVO jobBoardVO); //게시글 삭제
}
