package com.eventi.left.money.mapper;

import java.util.List;

import com.eventi.left.money.service.MoneyVO;

public interface MoneyMapper {
	
	public List<MoneyVO> moneyList(); //리스트 조회
	
	public List<MoneyVO> userMoneyList(String userId); //특정회원 리스트 조회
	
	public MoneyVO oneMoneySelect(String moNum); //입출금내역 1건 조회
	
	public int insertMoney(MoneyVO vo); //추가(입금, 출금요청)
	
	public int updateMoney(MoneyVO vo); //수정(출금 확인여부 업데이트)
	
	
	

}
