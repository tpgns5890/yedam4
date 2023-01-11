package com.eventi.left.job.service;

import java.util.List;

public interface JobService {
	
	public List<JobBoardVO> getJobList(JobBoardVO jobBoardVO); //구인게시글전체조회
	public JobBoardVO getJob(JobBoardVO jobBoardVO); //단건조회
	public int getJobUpdate(JobBoardVO jobBoardVO); //게시글수정
	public List<JobBoardVO> getSeekerAll(JobBoardVO jobBoardVO); //메인(구직자조회)
}
