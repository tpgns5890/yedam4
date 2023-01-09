package com.eventi.left.resume.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.eventi.left.resume.mapper.ResumeBoardMapper;
import com.eventi.left.resume.service.ResumeBoardVO;
import com.eventi.left.resume.service.ResumeService;

public class ResumeServiceImpl implements ResumeService{

	@Autowired 
	ResumeBoardMapper resumeMapper;
	
	@Override
	public List<ResumeBoardVO> getResumeList(ResumeBoardVO resumeBoardVO) {
		// TODO Auto-generated method stub
		return resumeMapper.getResumeList(resumeBoardVO);
	}

	@Override
	public ResumeBoardVO getResume(ResumeBoardVO resumeBoardVO) {
		// TODO Auto-generated method stub
		return resumeMapper.getResume(resumeBoardVO);
	}

}
