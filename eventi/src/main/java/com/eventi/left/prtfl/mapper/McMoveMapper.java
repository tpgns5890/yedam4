package com.eventi.left.prtfl.mapper;

import java.util.List;

import com.eventi.left.prtfl.service.McMoveVO;

public interface McMoveMapper {
	//사회자별 전체동영상리스트
	public List<McMoveVO> moveList(McMoveVO mcMoveVO);
	
	//사회자별 동영상개수
	public int count(McMoveVO mcMoveVO);
	
	//다음 동영상 번호 찾기
	public String getSeq();
	
	//동영상추가
	public int moveInsert(McMoveVO mcMoveVO);
}
