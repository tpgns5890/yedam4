package com.eventi.left.job.mapper;

import java.util.List;

import com.eventi.left.job.service.JobBoardVO;

public interface JobBoardMapper {

	public List<JobBoardVO> getJobList(JobBoardVO jobBoardVO); //전체조회
	
	public JobBoardVO getJob(JobBoardVO jobBoardVO); //게시물 상세조회
	
	public int getJobUpdate(JobBoardVO jobBoardVO); //게시글 수정
	
	public int jobInsert(JobBoardVO jobBoardVO); //게시글 등록
	
	public int jobDelete(JobBoardVO jobBoardVO); //게시글 삭제
}
