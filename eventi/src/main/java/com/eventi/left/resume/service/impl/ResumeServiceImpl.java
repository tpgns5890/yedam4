package com.eventi.left.resume.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.member.service.MemberVO;
import com.eventi.left.resume.mapper.ResumeBoardMapper;
import com.eventi.left.resume.service.ResumeBoardVO;
import com.eventi.left.resume.service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService{

	@Autowired 
	ResumeBoardMapper resumeMapper;
	
	//구직자 전체조회(메인구인게시판)
	@Override
	public List<ResumeBoardVO> getResumeList(ResumeBoardVO resumeBoardVO) { 
		// TODO Auto-generated method stub
		return resumeMapper.getResumeList(resumeBoardVO);
	}

	//구직자 상세조회
	@Override
	public ResumeBoardVO getResumeDetail(ResumeBoardVO resumeBoardVO) { 
		// TODO Auto-generated method stub
		return resumeMapper.getResumeDetail(resumeBoardVO);
	}

	//구직자 전체조회(상세조회게시판)
	@Override
	public List<ResumeBoardVO> getResumeJob(ResumeBoardVO resumeBoardVO) {
		// TODO Auto-generated method stub
		return resumeMapper.getResumeJob(resumeBoardVO);
	}
	
	//구직신청폼 이동
	@Override
	public ResumeBoardVO getApplyForm(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return resumeMapper.getApplyForm(memberVO);
	}
	
	//구직신청폼 등록
	@Override
	public int ApplyInsert(ResumeBoardVO resumeBoardVO) {
		// TODO Auto-generated method stub
		return resumeMapper.ApplyInsert(resumeBoardVO);
	}
	//구직자 채용 
	@Override
	public int hireUpdate(ResumeBoardVO resumeBoardVO) {
		// TODO Auto-generated method stub
		return resumeMapper.hireUpdate(resumeBoardVO);
	}
	//구직자 미채용 
	@Override
	public int unHireUpdate(ResumeBoardVO resumeBoardVO) {
		// TODO Auto-generated method stub
		return resumeMapper.unHireUpdate(resumeBoardVO);
	}

	@Override
	public int hireUpdates(List<ResumeBoardVO> resumeBoardVO) {
		int i=0;
		for(ResumeBoardVO vo : resumeBoardVO) {
		resumeMapper.hireUpdate(vo);
		i++;
		}
		return i;
	}


	
}
