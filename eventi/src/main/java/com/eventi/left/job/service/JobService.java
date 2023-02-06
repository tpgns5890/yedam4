package com.eventi.left.job.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.service.FilesVO;

public interface JobService {
	
	//전체조회
	public List<JobBoardVO> getJobList(JobBoardVO jobBoardVO, PagingVO paging); 
	
	//게시물 상세조회
	public JobBoardVO getJob(JobBoardVO jobBoardVO); 
	
	//임시저장된 게시글 조회
	public List<JobBoardVO> jSave(JobBoardVO jobBoardVO);
	
	//게시글 등록
	public int jobInsert(JobBoardVO jobBoardVO, FilesVO filesVO, MultipartFile[] uploadFile); 
	
	//게시글수정
	public int getJobUpdate(JobBoardVO jobBoardVO, FilesVO filesVO, MultipartFile[] uploadFile); 
	
	//게시글 삭제
	public int jobDelete(JobBoardVO jobBoardVO);
	
	//마이페이지 구인게시글 조회
	public List<JobBoardVO> myJobList(JobBoardVO jobBoardVO, PagingVO paging);
	
	//마이페이지 구직신청한 게시글 조회
	public List<JobBoardVO> myApplyList(JobBoardVO jobBoardVO, PagingVO paging); 
}
