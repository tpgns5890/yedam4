package com.eventi.left.job.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.files.FileDto;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.job.mapper.JobBoardMapper;
import com.eventi.left.job.service.JobBoardVO;
import com.eventi.left.job.service.JobService;
import com.eventi.left.member.service.MemberVO;

@Service
public class JobServiceImpl implements JobService{

	@Autowired 
	JobBoardMapper jobmapper;
	@Autowired FilesMapper filesMapper;
	@Autowired UploadFileMethod newUp;
	
	//게시글 전체조회
	@Override
	public List<JobBoardVO> getJobList(JobBoardVO jobBoardVO, PagingVO paging) {
		paging.setTotalRecord(jobmapper.count(jobBoardVO));
		paging.setPageUnit(5);
		jobBoardVO.setFirst(paging.getFirst());
		jobBoardVO.setLast(paging.getLast());
		return jobmapper.getJobList(jobBoardVO);
	}
	
	//상세(단건)조회
	@Override
	public JobBoardVO getJob(JobBoardVO jobBoardVO) {
		jobmapper.seeUp(jobBoardVO);
		return jobmapper.getJob(jobBoardVO);
	}
	
	//게시글등록
	@Override
	public int jobInsert(JobBoardVO jobBoardVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		//사진등록
		int r = jobmapper.jobInsert(jobBoardVO);
		List<FileDto> list = new ArrayList<FileDto>();
		try {
			list = newUp.uploadFiles(uploadFile, jobBoardVO.getJobNo(), "T03");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	//게시글수정
	@Override
	public int getJobUpdate(JobBoardVO jobBoardVO, MultipartFile[] uploadFile) {
		//List<FileDto> list = new ArrayList<FileDto>();
		//사진등록
		/*
		 * String realFolder = "/files/job"; File dir = new File(realFolder);
		 * if(!dir.isDirectory()) { dir.mkdirs(); } //파일 이름 저장 try { list =
		 * newUp.uploadFiles(uploadFile, jobBoardVO.getJobNo(), "T03"); }catch(Exception
		 * e){ e.printStackTrace(); } String img = uploadFile.getOriginalFilename();
		 * 
		 * //VO에 IMG 부분에 파일 이름 저장 jobBoardVO.setImg(img);
		 */
		return jobmapper.getJobUpdate(jobBoardVO);
	}
	
	//게시글삭제
	@Override
	public int jobDelete(JobBoardVO jobBoardVO) {
		// TODO Auto-generated method stub
		return jobmapper.jobDelete(jobBoardVO);
	}
	
	//임시저장된 게시글 가져오기 
	@Override
	public List<JobBoardVO> jSave(JobBoardVO jobBoardVO) {
		// TODO Auto-generated method stub
		return jobmapper.jSave(jobBoardVO);
	}
	
	//마이페이지 구인게시글 전체조회
	@Override
	public List<JobBoardVO> myJobList(JobBoardVO jobBoardVO, PagingVO paging) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		jobBoardVO.setUserId(sessionId);
		
		paging.setTotalRecord(jobmapper.count(jobBoardVO));
		paging.setPageUnit(5);
		jobBoardVO.setFirst(paging.getFirst());
		jobBoardVO.setLast(paging.getLast());
		
		return jobmapper.myJobList(jobBoardVO);
	}
	
	//마이페이지 구직신청게시글 전체조회
	@Override
	public List<JobBoardVO> myApplyList(JobBoardVO jobBoardVO, PagingVO paging) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		jobBoardVO.setUserId(sessionId);
		
		paging.setTotalRecord(jobmapper.count(jobBoardVO));
		paging.setPageUnit(5);
		jobBoardVO.setFirst(paging.getFirst());
		jobBoardVO.setLast(paging.getLast());
		return jobmapper.myApplyList(jobBoardVO);
	}
	

}
