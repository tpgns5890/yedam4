package com.eventi.left.punish.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.punish.mapper.PunishMapper;
import com.eventi.left.punish.service.PunishService;
import com.eventi.left.punish.service.PunishVO;

@Service
public class PunishServiceImpl implements PunishService{
	@Autowired PunishMapper punishMapper;

	//신고하기
	@Override
	public int punishInsert(PunishVO punishVO) {
		//이미 게시글이 신고된 상태라면
		if(punishMapper.punishCheck(punishVO) > 0) {
			return 0;
		}
		return punishMapper.punishInsert(punishVO);
	}
}
