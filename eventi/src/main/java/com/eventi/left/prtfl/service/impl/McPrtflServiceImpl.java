package com.eventi.left.prtfl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.files.FileDto;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.prtfl.mapper.McPrtflMapper;
import com.eventi.left.prtfl.service.McPrtflService;
import com.eventi.left.prtfl.service.McPrtflVO;
import com.eventi.left.reply.service.ReplyVO;

@Service
public class McPrtflServiceImpl implements McPrtflService{
	@Autowired McPrtflMapper mcPrtflMapper;
	@Autowired FilesMapper filesMapper;
	@Autowired UploadFileMethod newUp;
		
	//전체조회
	@Override
	public List<McPrtflVO> mcAll(McPrtflVO McPrtfVO, PagingVO paging) {
		paging.setTotalRecord(mcPrtflMapper.count(McPrtfVO));
		paging.setPageUnit(6);
		McPrtfVO.setFirst(paging.getFirst());
		McPrtfVO.setLast(paging.getLast());
		
		return mcPrtflMapper.mcAll(McPrtfVO);
	}
	
	//건별조회
	@Override
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO) {
		mcPrtflMapper.inqUpdate(mcPrtflVO);
		return mcPrtflMapper.mcSelect(mcPrtflVO);
	}
	
	//전체 건수
	@Override
	public int count(McPrtflVO mcPrtflVO) {
		return mcPrtflMapper.count(mcPrtflVO);
	}

	//댓글 전체조회
	@Override
	public List<ReplyVO> mcReply(ReplyVO replyVO) {
		return mcPrtflMapper.mcReply(replyVO);
	}

	//사회자 입력
	@Override
	public int mcInsert(McPrtflVO mcPrtflVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		
		// 대표사진세팅.
		mcPrtflVO.setImg(uploadFile[0].getOriginalFilename());
		
		int r = mcPrtflMapper.mcInsert(mcPrtflVO);
		List<FileDto> list= new ArrayList<FileDto>();
	      //파일 업로드하는 기능 부르기+데베에 저장하기/첨부파일 테이블에 저장할 때 쓰임
	      try {
			list = newUp.uploadFiles(uploadFile, mcPrtflVO.getUserId(), "T06");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	//조회수 +1
	@Override
	public int inqUpdate(McPrtflVO mcPrtflVO) {
		return mcPrtflMapper.inqUpdate(mcPrtflVO);
	}

	//사회자 수정
	@Override
	public int mcUpdate(McPrtflVO mcPrtflVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		// 대표사진세팅.
	    mcPrtflVO.setImg(uploadFile[0].getOriginalFilename());
		
	    int r = mcPrtflMapper.mcUpdate(mcPrtflVO);
	    uploadFiles(uploadFile, mcPrtflVO);
	    
		return r;
	}
	
	//파일 업로드
	public void uploadFiles(MultipartFile[] uploadfile, McPrtflVO mcPrtflVO) {
		List<FileDto> list = new ArrayList<FileDto>();
		try {
			list = newUp.uploadFiles(uploadfile, mcPrtflVO.getUserId(), "T06"); // 대상구분 공모전
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
