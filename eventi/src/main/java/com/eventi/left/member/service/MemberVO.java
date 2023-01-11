package com.eventi.left.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {
	private String userId;
	private String userPassword;
	private String userEmail;
	private String userName;
	private int userPhone;
	private String userMessaging;
	private String userGrade;
	private int rprt;
	private String depotr;
	private String bank;
	private int accnt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date userRegDate;
	List<CrtfVO> crtfs;
}
