package com.eventi.left.notice.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.notice.mapper.NoticeMapper;
import com.eventi.left.notice.service.NoticeService;
import com.eventi.left.notice.service.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired 
	NoticeMapper nocmapper;
	@Autowired FilesMapper filesMapper;
	@Autowired UploadFileMethod newUp;
	
	//게시글 전체조회
	@Override
	public List<NoticeVO> noticeList(NoticeVO noticeVO, PagingVO paging) {
		paging.setTotalRecord(nocmapper.count(noticeVO));
		paging.setPageUnit(5);
		noticeVO.setFirst(paging.getFirst());
		noticeVO.setLast(paging.getLast());
		return nocmapper.noticeList(noticeVO);
	}
	
	//게시글등록
	@Override
	public int nocInsert(NoticeVO noticeVO, MultipartFile uploadFile) {
	// TODO Auto-generated method stub
		//사진등록
		String realFolder = "/files/noc";
		File dir = new File(realFolder);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
			
		//파일 이름 저장
		String img = uploadFile.getOriginalFilename();
			
		//VO에 IMG 부분에 파일 이름 저장
		noticeVO.setImg(img);
			
		return nocmapper.nocInsert(noticeVO);
		}
	
	//게시글 상세조회
	@Override
		public NoticeVO nocDetail(NoticeVO noticeVO) {
			nocmapper.seeUp(noticeVO);
			return nocmapper.nocDetail(noticeVO);
		}
	
	//게시글 수정
	@Override
	public int nocUpdate(NoticeVO noticeVO) {
		// TODO Auto-generated method stub
		return nocmapper.nocUpdate(noticeVO);
	}
	
	//게시글 삭제
	@Override
	public int nocDelete(NoticeVO noticeVO) {
		// TODO Auto-generated method stub
		return nocmapper.nocDelete(noticeVO);
	}

	@Override
	public int count(NoticeVO noticeVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//조회
	@Override
	public int seeUp(NoticeVO noticeVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//시퀀스 값 얻기
	@Override
	public String getSeq() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
