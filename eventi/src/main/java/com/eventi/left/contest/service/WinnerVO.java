package com.eventi.left.contest.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WinnerVO {
	String wNo;	 	  //수상번호
	String cNo;		  //공모전번호
	Integer grade;	  //선택등수
	Integer pay;	  //상금액
	String dNo;		  //디자인번호
	String userId;	  //우승자ID(회원ID)

}
