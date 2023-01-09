package com.eventi.left.resume.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ResumeService {
	public List<ResumeBoardVO> getResumeList(ResumeBoardVO resumeBoardVO); //구직자 전체조회
	public ResumeBoardVO getResume(ResumeBoardVO resumeBoardVO); //단건조회
}
