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
		return punishMapper.punishInsert(punishVO);
	}
}
