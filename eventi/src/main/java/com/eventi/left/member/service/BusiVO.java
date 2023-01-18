package com.eventi.left.member.service;

import java.sql.Array;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusiVO {
	private String userId;
	private String busiNum;
	private String presiName;
	private String busiStartDate;
	private String busiTitle;
	private String busiType;
	private Array busiArea;
	private Array busiStyle;
}
