package com.eventi.left.prtfl.mapper;

import java.util.List;

import com.eventi.left.prtfl.service.EventImgVO;

public interface EventImgMapper {
	//업체별 행사 이미지 리스트
	public List<EventImgVO> getEvent(EventImgVO eventImgVO);

	//전체건수
	public int count(EventImgVO eventImgVO);
	
	//다음 이미지 번호 찾기
	public String getSeq();
	
	//행사 이미지 추가
	public int eventInsert(EventImgVO eventImgVO);
}
