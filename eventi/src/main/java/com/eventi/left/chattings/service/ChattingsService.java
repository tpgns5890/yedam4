package com.eventi.left.chattings.service;

import java.util.List;

public interface ChattingsService {
	//채팅방 불러오기
	public List<ChattingsVO> chatRoom(ChattingsVO chattingsVO);
	
	//채팅방 추가
	public String chatRoomInsert(List<ChattingsVO> chattingsVO);
	
	//채팅방 확인
	public int checkRoom(ChattingsVO chattingsVO);
}
