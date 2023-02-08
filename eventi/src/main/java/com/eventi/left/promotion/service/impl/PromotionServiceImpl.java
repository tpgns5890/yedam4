package com.eventi.left.promotion.service.impl;

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
import com.eventi.left.promotion.mapper.PromotionBoardMapper;
import com.eventi.left.promotion.service.PromotionService;
import com.eventi.left.promotion.service.PromotionVO;
import com.eventi.left.reply.service.ReplyVO;

@Service
public class PromotionServiceImpl implements PromotionService{
	
	@Autowired 
	PromotionBoardMapper proMapper;
	@Autowired 
	FilesMapper filesMapper;
	@Autowired 
	UploadFileMethod newUp;
	
	//홍보게시글 전체조회
	@Override
	public List<PromotionVO> proList(PromotionVO promotionVO, PagingVO paging) {
		paging.setTotalRecord(proMapper.count(promotionVO));
		paging.setPageUnit(5);
		promotionVO.setFirst(paging.getFirst());
		promotionVO.setLast(paging.getLast());
		return proMapper.proList(promotionVO);
	}
	
	//게시글 상세조회
	@Override
	public PromotionVO proDetail(PromotionVO promotionVO) {
		proMapper.seeUp(promotionVO);
		return proMapper.proDetail(promotionVO);
	}	

	//게시글 등록
	@Override
	public int proInsert(PromotionVO promotionVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		//사진등록
		int r = proMapper.proInsert(promotionVO);
		List<FileDto> list = new ArrayList<FileDto>();
		try {
			list = newUp.uploadFiles(uploadFile, promotionVO.getProNo(), "T02");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	//게시글 수정
	@Override
	public int proUpdate(PromotionVO promotionVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		//사진첨부 
		if(uploadFile[0].getOriginalFilename() !="") {
			promotionVO.setImg(uploadFile[0].getOriginalFilename());
		}
		uploadFiles(uploadFile, promotionVO);
		int r = proMapper.proUpdate(promotionVO);
		
		return r; 
	}

	//파일 업로드
	public void uploadFiles(MultipartFile[] uploadFile, PromotionVO promotionVO) {
		List<FileDto> list = new ArrayList<FileDto>();
		try {
			list = newUp.uploadFiles(uploadFile, promotionVO.getProNo(), "T02");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//게시글 삭제
	@Override
	public int proDelete(PromotionVO promotionVO) {
		// TODO Auto-generated method stub
		return proMapper.proDelete(promotionVO);
	}
	
	//댓글 조회
	@Override
	public List<ReplyVO> proReply(ReplyVO replyVO) {
		return proMapper.proReply(replyVO);
	}
	
	//시퀀스 값 얻기
	@Override
	public String getSeq() {
		// TODO Auto-generated method stub
		return proMapper.getSeq();
	}
	
	//조회수
	@Override
	public int seeUp(PromotionVO promotionVO) {
		// TODO Auto-generated method stub
		return proMapper.seeUp(promotionVO);
	}
	
	//마이페이지 작성한 홍보게시글 조회
	@Override
	public List<PromotionVO> myPromotionList(PromotionVO promotionVO, PagingVO paging) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		promotionVO.setUserId(sessionId);
		
		paging.setTotalRecord(proMapper.count(promotionVO));
		paging.setPageUnit(5);
		promotionVO.setFirst(paging.getFirst());
		promotionVO.setLast(paging.getLast());
		
		return proMapper.myPromotionList(promotionVO);
	}

	
}
