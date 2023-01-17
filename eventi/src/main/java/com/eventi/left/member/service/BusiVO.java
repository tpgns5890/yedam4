package com.eventi.left.member.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusiVO {
	private String userId;
	private String busiNum;
	private String busiStartDate;
	private String busiTitle;
	private String busiArea;
	private String busiStyle;
}
