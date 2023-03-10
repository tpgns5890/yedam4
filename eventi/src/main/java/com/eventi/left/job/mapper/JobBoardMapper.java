package com.eventi.left.job.mapper;

import java.util.List;

import com.eventi.left.job.service.JobBoardVO;

public interface JobBoardMapper {
	
	//전체조회
	public List<JobBoardVO> getJobList(JobBoardVO jobBoardVO); 
	
	//게시물 상세조회
	public JobBoardVO getJob(JobBoardVO jobBoardVO); 
	
	//임시저장된 게시글 조회
	public List<JobBoardVO> jSave(JobBoardVO jobBoardVO);
		
	//게시글 수정
	public int getJobUpdate(JobBoardVO jobBoardVO); 
	
	//게시글 등록
	public int jobInsert(JobBoardVO jobBoardVO); 
	
	//게시글 삭제
	public int jobDelete(JobBoardVO jobBoardVO);
	
	//게시글 개수
	public int count(JobBoardVO jobBoardVO);
	
	//게시물 조회수 
	public int seeUp(JobBoardVO jobBoardVO);
	
	//마이페이지 구인게시글 조회
	public List<JobBoardVO> myJobList(JobBoardVO jobBoardVO); 
	
	//마이페이지 구직신청한 게시글 조회
	public List<JobBoardVO> myApplyList(JobBoardVO jobBoardVO); 
	
	//좋아요한 게시글들
	List<JobBoardVO> likeList(String userId);
}
