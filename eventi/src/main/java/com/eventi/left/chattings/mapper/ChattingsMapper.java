package com.eventi.left.chattings.mapper;

import java.util.List;

import com.eventi.left.chattings.service.ChattingsVO;

public interface ChattingsMapper {
	//채팅방 불러오기
	public List<ChattingsVO> chatRoom(ChattingsVO chattingsVO);
	
	//채팅방 추가
	public int chatRoomInsert(ChattingsVO chattingsVO);
	
	//채팅방 확인
	public int checkRoom(ChattingsVO chattingsVO);
	
	//다음 채팅방 번호가져오기
	public String getSeq();
}
