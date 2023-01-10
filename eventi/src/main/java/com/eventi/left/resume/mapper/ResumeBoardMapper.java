 package com.eventi.left.resume.mapper;

import java.util.List;

import com.eventi.left.member.service.MemberVO;
import com.eventi.left.resume.service.ResumeBoardVO;

public interface ResumeBoardMapper {

	public List<ResumeBoardVO> getResumeList(ResumeBoardVO resumeBoardVO); //구직자 전체조회(메인구인게시판-여러개시글)
	public List<ResumeBoardVO> getResumeJob(ResumeBoardVO resumeBoardVO); //구직자 전체조회(상세구인게시판-같은게시글)
	public ResumeBoardVO getResumeDetail(ResumeBoardVO resumeBoardVO); //단건조회
	public ResumeBoardVO getApplyForm(MemberVO memberVO); //구직신청폼
}
                                     