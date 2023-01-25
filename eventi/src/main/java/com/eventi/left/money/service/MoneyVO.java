package com.eventi.left.money.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoneyVO {
	private String moNum; //고유번호
	private String bankName; //은행사
	private String bankAccount;  //계좌번호
	private String moType;  //입출금타입
	private int moPrice;  //금액
	private String userId;  //유저 ID
	private String payNo;	//결제번호
	private String settYN;	//승인여부
}