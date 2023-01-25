package com.eventi.left.chattings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.chattings.mapper.ChattingsMapper;
import com.eventi.left.chattings.service.ChattingsService;
import com.eventi.left.chattings.service.ChattingsVO;

@Service
public class ChattingsServiceImpl implements ChattingsService{
	@Autowired ChattingsMapper chattingsMapper;
	
	//채팅방 불러오기
	@Override
	public List<ChattingsVO> chatRoom(ChattingsVO chattingsVO) {
		return chattingsMapper.chatRoom(chattingsVO);
	}

	//채팅방 추가
	@Override
	public String chatRoomInsert(List<ChattingsVO> list) {
		System.out.println("채팅방등록: " + list);
		String cId = chattingsMapper.getSeq();
		System.out.println("채팅방번호: " + cId);
		
		for(int i=0; i<list.size(); i++) {
			list.get(i).setCId(cId);
			chattingsMapper.chatRoomInsert(list.get(i));
		}
		
		return cId;
	}
	
}
