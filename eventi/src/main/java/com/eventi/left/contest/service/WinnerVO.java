package com.eventi.left.contest.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WinnerVO {
	private String wNo; // 수상번호
	private String coNo; // 공모전번호
	private int grade; // 선택등수
	private int wPay; // 상금액
	private String dNo; // 디자인번호

	// 정보처리를 위한 필드선언.
	private String[] winnerPay; // form 입력 배열.
	private String[] dgnNo;
	
	
	public String getwNo() {
		return wNo;
	}

	public void setwNo(String wNo) {
		this.wNo = wNo;
	}

	public int getwPay() {
		return wPay;
	}

	public void setwPay(int wPay) {
		this.wPay = wPay;
	}

	public String getdNo() {
		return dNo;
	}

	public void setdNo(String dNo) {
		this.dNo = dNo;
	}

	private String userId; // 우승자ID(회원ID)

}
