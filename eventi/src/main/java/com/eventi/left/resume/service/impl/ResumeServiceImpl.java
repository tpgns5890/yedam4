package com.eventi.left.resume.service.impl;

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
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.resume.mapper.ResumeBoardMapper;
import com.eventi.left.resume.service.ResumeBoardVO;
import com.eventi.left.resume.service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService{

	@Autowired 
	ResumeBoardMapper resumeMapper;
	@Autowired FilesMapper filesMapper;
	@Autowired UploadFileMethod newUp;
	
	//구직자 전체조회(메인구인게시판)
	@Override
	public List<ResumeBoardVO> getResumeList(ResumeBoardVO resumeBoardVO, PagingVO paging) { 
		paging.setTotalRecord(resumeMapper.count(resumeBoardVO));
		paging.setPageUnit(6);
		resumeBoardVO.setFirst(paging.getFirst());
		resumeBoardVO.setLast(paging.getLast());
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
	public List<ResumeBoardVO> getResumeJob(ResumeBoardVO resumeBoardVO, PagingVO paging) {
		paging.setTotalRecord(resumeMapper.count(resumeBoardVO));
		paging.setPageUnit(5);
		paging.setPageUnit(5);
		resumeBoardVO.setFirst(paging.getFirst());
		resumeBoardVO.setLast(paging.getLast());
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
	public int ApplyInsert(ResumeBoardVO resumeBoardVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		//사진등록 
		int r = resumeMapper.ApplyInsert(resumeBoardVO);
		List<FileDto> list = new ArrayList<FileDto>();
		try {
			list = newUp.uploadFiles(uploadFile, resumeBoardVO.getResumeNo(), "T13");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return r;
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
	
	//구직자 부분채용
	@Override
	public int hireUpdates(List<ResumeBoardVO> resumeList) {
		int i=0;
		for(ResumeBoardVO vo : resumeList) {
			resumeMapper.hireUpdate(vo);
			i++;
		}
		return i;
	}
	//구직자 부분미채용
	@Override
	public int unHireUpdates(List<ResumeBoardVO> resumeCancel) {
		int i=0;
		for(ResumeBoardVO vo : resumeCancel) {
			resumeMapper.unHireUpdate(vo);
			i++;
		}
		return i;
	}
	//시퀀스 값 찾기
	@Override
	public String getSeq() {
		// TODO Auto-generated method stub
		return resumeMapper.getSeq();
	}
	//구직자 정보조회
	@Override
	public List<ResumeBoardVO> seekerInfo(ResumeBoardVO resumeBoardVO, PagingVO paging) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		resumeBoardVO.setSeekerId(sessionId);
		
		paging.setTotalRecord(resumeMapper.count(resumeBoardVO));
		paging.setPageUnit(5);
		resumeBoardVO.setFirst(paging.getFirst());
		resumeBoardVO.setLast(paging.getLast());
		return resumeMapper.seekerInfo(resumeBoardVO);
	}
	//게시글 개수
	@Override
	public int count(ResumeBoardVO resumeBoardVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	//마이페이지 구직신청 삭제
	@Override
	public int applyDelete(ResumeBoardVO resumeBoardVO) {
		// TODO Auto-generated method stub
		return resumeMapper.applyDelete(resumeBoardVO);
	}
}