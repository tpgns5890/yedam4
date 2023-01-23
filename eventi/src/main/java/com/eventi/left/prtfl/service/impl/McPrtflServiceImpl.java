package com.eventi.left.prtfl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.SessionUtil;
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
	public List<McPrtflVO> mcAll(McPrtflVO McPrtfVO) {
		return mcPrtflMapper.mcAll(McPrtfVO);
	}
	
	//건별조회
	@Override
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO) {
		return mcPrtflMapper.mcSelect(mcPrtflVO);
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
		
		int r = mcPrtflMapper.mcInsert(mcPrtflVO);
		List<FileDto> list= new ArrayList<FileDto>();
	      //파일 업로드하는 기능 부르기+데베에 저장하기/첨부파일 테이블에 저장할 때 쓰임
	      try {
			list = newUp.uploadFiles(uploadFile, user.getUserId(), "T06");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	//조회수 +1
	@Override
	public int getSeq(McPrtflVO mcPrtflVO) {
		return mcPrtflMapper.getSeq(mcPrtflVO);
	}

	//사회자 수정
	@Override
	public int mcUpdate(McPrtflVO mcPrtlVo) {
		return mcPrtflMapper.mcUpdate(mcPrtlVo);
	}
}
