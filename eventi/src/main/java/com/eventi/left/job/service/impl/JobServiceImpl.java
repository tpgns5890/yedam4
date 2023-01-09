package com.eventi.left.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.job.mapper.JobBoardMapper;
import com.eventi.left.job.service.JobBoardVO;
import com.eventi.left.job.service.JobService;

@Service
public class JobServiceImpl implements JobService{

	@Autowired 
	JobBoardMapper jobmapper;
	
	@Override
	public List<JobBoardVO> getJobList(JobBoardVO jobBoardVO) {
		// TODO Auto-generated method stub
		return jobmapper.getJobList(jobBoardVO);
	}

	@Override
	public JobBoardVO getJob(JobBoardVO jobBoardVO) {
		// TODO Auto-generated method stub
		return jobmapper.getJob(jobBoardVO);
	}

}
