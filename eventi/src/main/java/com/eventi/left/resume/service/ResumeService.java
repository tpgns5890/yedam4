package com.eventi.left.resume.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.member.service.MemberVO;

@Service
public interface ResumeService {
	
	//구직자 전체조회(메인구인게시판-여러개시글)
	public List<ResumeBoardVO> getResumeList(ResumeBoardVO resumeBoardVO); 
		
	//구직자 전체조회(상세구인게시판-같은게시글)
	public List<ResumeBoardVO> getResumeJob(ResumeBoardVO resumeBoardVO); 
		
	//단건조회
	public ResumeBoardVO getResumeDetail(ResumeBoardVO resumeBoardVO); 
		
	//구직신청폼
	public ResumeBoardVO getApplyForm(MemberVO memberVO); 
		
	//구직신청폼 등록
	public int ApplyInsert(ResumeBoardVO resumeBoardVO, FilesVO filesVO, MultipartFile[] uploadFile);
	
	//구직자 채용
	public int hireUpdate(ResumeBoardVO resumeBoardVO);
	
	//구직자 미채용 
	public int unHireUpdate(ResumeBoardVO resumeBoardVO);
	
	//구직자 부분채용
	public int hireUpdates(List<ResumeBoardVO> resumeBoardVO); 
	
	//구직자 부분미채용 
	public int unHireUpdates(List<ResumeBoardVO> resumeBoardVO);
	
	//시퀀스 값 찾기
	public String getSeq();
	
	//게시글 개수
	public int count(ResumeBoardVO resumeBoardVO);
	
	//마이페이지 구직자정보 조회
	public List<ResumeBoardVO> seekerInfo(ResumeBoardVO resumeBoardVO, PagingVO paging);
	
	//마이페이지 구직신청 삭제
	public int applyDelete(ResumeBoardVO resumeBoardVO);
}
