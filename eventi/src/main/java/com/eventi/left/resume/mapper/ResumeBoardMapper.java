package com.eventi.left.resume.mapper;

import java.util.List;

import com.eventi.left.resume.service.ResumeBoardVO;

public interface ResumeBoardMapper {

	public List<ResumeBoardVO> getResumeList(ResumeBoardVO resumeBoardVO); //구직자 전체조회
	public ResumeBoardVO getResume(ResumeBoardVO resumeBoardVO); //단건조회
}
                                     