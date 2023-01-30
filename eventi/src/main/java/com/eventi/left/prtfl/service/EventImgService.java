package com.eventi.left.prtfl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.service.FilesVO;

public interface EventImgService {
	//업체별 행사 이미지 리스트
	public List<EventImgVO> getEvent(EventImgVO eventImgVO, PagingVO paging);
	
	//다음 이미지 번호 찾기
	public String getSeq();
	
	//행사 이미지 추가
	public int eventInsert(EventImgVO eventImgVO, FilesVO filesVO, MultipartFile[] uploadFile);
}
