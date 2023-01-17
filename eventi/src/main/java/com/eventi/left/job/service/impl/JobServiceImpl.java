package com.eventi.left.job.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.job.mapper.JobBoardMapper;
import com.eventi.left.job.service.JobBoardVO;
import com.eventi.left.job.service.JobService;

@Service
public class JobServiceImpl implements JobService{

	@Autowired 
	JobBoardMapper jobmapper;
	
	//게시글 전체조회
	@Override
	public List<JobBoardVO> getJobList(JobBoardVO jobBoardVO, PagingVO paging) {
		paging.setTotalRecord(jobmapper.count(jobBoardVO));
		paging.setPageUnit(5);
		jobBoardVO.setFirst(jobBoardVO.getFirst());
		jobBoardVO.setLast(jobBoardVO.getLast());
		return jobmapper.getJobList(jobBoardVO);
	}
	
	//단건조회
	@Override
	public JobBoardVO getJob(JobBoardVO jobBoardVO) {
		// TODO Auto-generated method stub
		return jobmapper.getJob(jobBoardVO);
	}
	
	//게시글수정
	@Override
	public int getJobUpdate(JobBoardVO jobBoardVO) {
		// TODO Auto-generated method stub
		return jobmapper.getJobUpdate(jobBoardVO);
	}
	//게시글등록
	@Override
	public int jobInsert(JobBoardVO jobBoardVO, MultipartFile uploadFile) {
		// TODO Auto-generated method stub
		//사진등록
		String realFolder = "/files/job";
		File dir = new File(realFolder);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		//파일 이름 저장
		String img = uploadFile.getOriginalFilename();
		
		//VO에 IMG 부분에 파일 이름 저장
		jobBoardVO.setImg(img);
		
		return jobmapper.jobInsert(jobBoardVO);
	}
	//게시글삭제
	@Override
	public int jobDelete(JobBoardVO jobBoardVO) {
		// TODO Auto-generated method stub
		return jobmapper.jobDelete(jobBoardVO);
	}
	

}
