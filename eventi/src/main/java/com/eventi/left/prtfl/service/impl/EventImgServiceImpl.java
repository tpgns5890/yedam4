package com.eventi.left.prtfl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.FileDto;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.prtfl.mapper.EventImgMapper;
import com.eventi.left.prtfl.service.EventImgService;
import com.eventi.left.prtfl.service.EventImgVO;

@Service
public class EventImgServiceImpl implements EventImgService{
	@Autowired EventImgMapper eventImgMapper;
	@Autowired FilesMapper filesMapper;
	@Autowired UploadFileMethod newUp;
	
	//업체별 행사 리스트
	@Override
	public List<EventImgVO> getEvent(EventImgVO eventImgVO, PagingVO paging) {
		paging.setTotalRecord(eventImgMapper.count(eventImgVO));
		paging.setPageUnit(3);
		eventImgVO.setFirst(paging.getFirst());
		eventImgVO.setLast(paging.getLast());
		
		return eventImgMapper.getEvent(eventImgVO);
	}

	//행사이미지 추가
	@Override
	public int eventInsert(EventImgVO eventImgVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		int r = eventImgMapper.eventInsert(eventImgVO);
		
		List<FileDto> list= new ArrayList<FileDto>();
	      //파일 업로드하는 기능 부르기+데베에 저장하기/첨부파일 테이블에 저장할 때 쓰임
	      try {
			list = newUp.uploadFiles(uploadFile, eventImgVO.getEventNo(), "T08");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	//다음 이미지 번호 찾기
	@Override
	public String getSeq() {
		return eventImgMapper.getSeq();
	}

}
