package com.eventi.left.resume.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eventi.left.member.service.MemberVO;

@Service
public interface ResumeService {
	public List<ResumeBoardVO> getResumeList(ResumeBoardVO resumeBoardVO); //구직자 전체조회(메인구인게시판)
	public List<ResumeBoardVO> getResumeJob(ResumeBoardVO resumeBoardVO); //구직자 전체조회(상세구인게시판)
	public ResumeBoardVO getResumeDetail(ResumeBoardVO resumeBoardVO); //단건조회
	public ResumeBoardVO getApplyForm(MemberVO memberVO); //구직신청폼
}
