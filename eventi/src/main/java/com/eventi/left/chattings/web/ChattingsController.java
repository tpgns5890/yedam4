package com.eventi.left.chattings.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.chattings.service.ChattingsService;
import com.eventi.left.chattings.service.ChattingsVO;

@Controller
public class ChattingsController {
	@Autowired ChattingsService chattingsService;
	
	//채팅방 조회
	@PostMapping("/chatSelect")
	@ResponseBody
	public List<ChattingsVO> chatSelect(@RequestBody ChattingsVO chattingsVO){
		List<ChattingsVO> list = chattingsService.chatRoom(chattingsVO);
		return list;
	}
	
	//채팅방추가
	@PostMapping("/chatInsert")
	@ResponseBody
	public String chatInsert(@RequestBody List<ChattingsVO> list) {
		return chattingsService.chatRoomInsert(list);
	}
}
