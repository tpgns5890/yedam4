package com.eventi.left.money.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.money.mapper.MoneyMapper;
import com.eventi.left.money.service.MoneyService;
import com.eventi.left.money.service.MoneyVO;

@Service
public class MoneyServiceImpl implements MoneyService{
	
	@Autowired MoneyMapper mapper;

	@Override
	public List<MoneyVO> moneyList() {
		return mapper.moneyList();
	}

	@Override
	public List<MoneyVO> userMoneyList(String userId) {
		return mapper.userMoneyList(userId);
	}

	@Override
	public MoneyVO oneMoneySelect(String moNum) {
		return mapper.oneMoneySelect(moNum);
	}

	@Override
	public int insertMoney(MoneyVO vo) {
		return mapper.insertMoney(vo);
	}

	@Override
	public int updateMoney(MoneyVO vo) {
		return mapper.updateMoney(vo);
	}
	


}
