package com.eventi.left.bboard.service.impl;

import java.io.File;
import java.io.IOException;
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
import com.eventi.left.reply.mapper.ReplyMapper;
import com.eventi.left.reply.service.ReplyVO;

@Service
public class BboardServiceImpl implements BboardService{
	
	@Autowired BboardMapper bboardMapper;
	@Autowired FilesMapper filesMapper;
	@Autowired UploadFileMethod newUp;
	@Autowired ReplyMapper replyMapper;
	
	//전체 조회
	@Override
	public List<BboardVO> bboardList(BboardVO bboardVO, PagingVO paging) {
		paging.setTotalRecord(bboardMapper.count(bboardVO));
		paging.setPageUnit(15);
		bboardVO.setFirst(paging.getFirst());
		bboardVO.setLast(paging.getLast());
		
		return bboardMapper.bboardList(bboardVO);
	}
	
	//단건 조회
	@Override
	public BboardVO bboardSelect(BboardVO bboardVO) {
		//조회수 +1
		bboardMapper.inqUpdate(bboardVO);
		return bboardMapper.bboardSelect(bboardVO);
	}
	
	//임시저장된 게시글 가져오기
	@Override
	public BboardVO bSave(BboardVO bboardVO) {
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
	public int bboardUpdate(BboardVO bboardVO, MultipartFile uploadFile) {
		// 사진 등록
		String realFolder = "/files/bboard";
		File dir = new File(realFolder);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
					
		//파일 이름 저장
		String img = uploadFile.getOriginalFilename();
					
		//VO에 IMG 부분에 파일 이름 저장
		bboardVO.setImg(img);
		
		return bboardMapper.bboardUpdate(bboardVO);
	}
	
	//삭제
	@Override
	public int bboardDelete(BboardVO bboardVO) {
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
