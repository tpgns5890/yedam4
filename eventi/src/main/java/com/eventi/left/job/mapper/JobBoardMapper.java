package com.eventi.left.job.mapper;

import java.util.List;

import com.eventi.left.job.service.JobBoardVO;

public interface JobBoardMapper {

	public List<JobBoardVO> getJobList(JobBoardVO jobBoardVO); 
}
