package com.eventi.left.bboard.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.bboard.mapper.BboardMapper;
import com.eventi.left.bboard.service.BboardService;
import com.eventi.left.bboard.service.BboardVO;
import com.eventi.left.common.PagingVO;
import com.eventi.left.files.FileDto;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.likes.mapper.LikesMapper;
import com.eventi.left.likes.service.LikesVO;
import com.eventi.left.reply.mapper.ReplyMapper;
import com.eventi.left.reply.service.ReplyVO;

@Service
public class BboardServiceImpl implements BboardService{
	
	@Autowired BboardMapper bboardMapper;
	@Autowired FilesMapper filesMapper;
	@Autowired UploadFileMethod newUp;
	@Autowired ReplyMapper replyMapper;
	@Autowired LikesMapper likesMapper;
	
	//전체 조회
	@Override
	public List<BboardVO> bboardList(BboardVO bboardVO, PagingVO paging) {
		paging.setTotalRecord(bboardMapper.count(bboardVO));
		paging.setPageUnit(10);
		bboardVO.setFirst(paging.getFirst());
		bboardVO.setLast(paging.getLast());
		
		return bboardMapper.bboardList(bboardVO);
	}
	
	//단건 조회
	@Override
	public BboardVO bboardSelect(BboardVO bboardVO) {
		return bboardMapper.bboardSelect(bboardVO);
	}
	
	//조회수 +1 
	@Override
	public int inqUpdate(BboardVO bboardVO) {
		return bboardMapper.inqUpdate(bboardVO);
	}

	
	//임시저장된 게시글 가져오기
	@Override
	public List<BboardVO> bSave(BboardVO bboardVO) {
		return bboardMapper.bSave(bboardVO);
	}
	
	//좋아요 조회
	@Override
	public List<BboardVO> bboardLike(BboardVO bboardVO) {
		return bboardMapper.bboardLike(bboardVO);
	}

	//등록
	@Override
	public int bboardInsert(BboardVO bboardVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		int r = bboardMapper.bboardInsert(bboardVO);
		 List<FileDto> list= new ArrayList<FileDto>();
	      //파일 업로드하는 기능 부르기+데베에 저장하기/첨부파일 테이블에 저장할 때 쓰임
	      try {
			list = newUp.uploadFiles(uploadFile, bboardVO.getBBoardNo(), bboardVO.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	//수정
	@Override
	public int bboardUpdate(BboardVO bboardVO, MultipartFile[] uploadFile) {
		//내용 수정
		int r = bboardMapper.bboardUpdate(bboardVO);
		
		List<FileDto> list= new ArrayList<FileDto>();
		//파일 업로드하는 기능 부르기+데베에 저장하기/첨부파일 테이블에 저장할 때 쓰임
		try {
			list = newUp.updateFiles(uploadFile, bboardVO.getBBoardNo(), bboardVO.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	//삭제
	@Override
	public int bboardDelete(BboardVO bboardVO) {
		//좋아요 삭제
		LikesVO like = new LikesVO();
		like.setTargetNo(bboardVO.getBBoardNo());
		like.setCategory(bboardVO.getType());
		likesMapper.likeDelete(like);
		
		//댓글 삭제
		ReplyVO reply = new ReplyVO();
		reply.setReplyTgt(bboardVO.getBBoardNo());
		reply.setBoardCat(bboardVO.getType());
		replyMapper.replySelectDelete(reply);
		
		//파일 삭제
		filesMapper.deleteFile(bboardVO.getBBoardNo());
		
		return bboardMapper.bboardDelete(bboardVO);
	}
	
	//댓글 리스트
	@Override
	public List<ReplyVO> bboardReply(ReplyVO reply) {
		return bboardMapper.bboardReply(reply);
	}
	
	//시퀀스값 얻기
	@Override
	public String getSeq() {
		return bboardMapper.getSeq();
	}
}
