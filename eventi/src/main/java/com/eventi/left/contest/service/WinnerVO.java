package com.eventi.left.contest.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WinnerVO {
	private String wNo;	 	  //수상번호
	private String coNo;		  //공모전번호
	private Integer grade;	  //선택등수
	private Integer wPay;	  //상금액
	private String dNo;		  //디자인번호
	private String userId;	  //우승자ID(회원ID)

}
