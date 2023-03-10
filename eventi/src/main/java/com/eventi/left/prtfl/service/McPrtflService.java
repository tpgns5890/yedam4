package com.eventi.left.prtfl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.reply.service.ReplyVO;

public interface McPrtflService {
	//사회자 전체 조회
	public List<McPrtflVO> mcAll(McPrtflVO McPrtflVO, PagingVO paging);
	
	//사회자 건별 조회
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO);
	
	//사회자 등록 여부
	public int checkMc(McPrtflVO mcPrtflVO);
	
	//전체 건수
	public int count(McPrtflVO mcPrtflVO);
	
	//사회자 정보 입력
	public int mcInsert(McPrtflVO mcPrtflVO, FilesVO filesVO, MultipartFile[] uploadFile);
	
	//조회수 +1
	public int inqUpdate(McPrtflVO mcPrtflVO);
	
	//사회자 댓글 조회
	public List<ReplyVO> mcReply(ReplyVO replyVO);
	
	//사회자 수정
	public int mcUpdate(McPrtflVO mcPrtlVO, FilesVO filesVO, MultipartFile[] uploadFile);

	//사회자 삭제
	public int mcDelete(McPrtflVO mcPrtlVO);
}
