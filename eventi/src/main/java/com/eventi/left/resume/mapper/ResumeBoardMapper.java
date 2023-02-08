 package com.eventi.left.resume.mapper;

import java.util.List;

import com.eventi.left.member.service.MemberVO;
import com.eventi.left.reply.service.ReplyVO;
import com.eventi.left.resume.service.ResumeBoardVO;

public interface ResumeBoardMapper {
	
	//구직자 전체조회(메인구인게시판-여러개시글)
	public List<ResumeBoardVO> getResumeList(ResumeBoardVO resumeBoardVO); 
	
	//구직자 전체조회(상세구인게시판-같은게시글)
	public List<ResumeBoardVO> getResumeJob(ResumeBoardVO resumeBoardVO); 
		
	//단건조회
	public ResumeBoardVO getResumeDetail(ResumeBoardVO resumeBoardVO); 
	
	//댓글 조회
	public List<ReplyVO> resReply(ReplyVO replyVO);
		
	//구직신청폼
	public ResumeBoardVO getApplyForm(MemberVO memberVO); 
	
	//구직신청폼 등록
	public int ApplyInsert(ResumeBoardVO resumeBoardVO);
	
	//구직자 채용
	public int hireUpdate(ResumeBoardVO resumeBoardVO); 
	
	//구직자 미채용 
	public int unHireUpdate(ResumeBoardVO resumeBoardVO);
	
	//시퀀스 값 찾기
	public String getSeq();
	
	//마이페이지 구직자정보 조회
	public List<ResumeBoardVO> seekerInfo(ResumeBoardVO resumeBoardVO); 
	
	//게시글 개수
	public int count(ResumeBoardVO resumeBoardVO);
	
	//마이페이지 구직신청 삭제
	public int applyDelete(ResumeBoardVO resumeBoardVO);
}
                                     