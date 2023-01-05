package com.eventi.left.member.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {
	private String userId;
	private String userPassword;
	private String userAddress;
	private String userName;
	private String userSsn;
	private int userPhone;
	private String userMessaging;
	private String userGrade;
	private int rprt;
	private String depotr;
	private String bank;
	private int accnt;
}
