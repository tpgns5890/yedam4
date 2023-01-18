package com.eventi.left.member.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusiVO {
	private String userId;
	private String busiNum;
	private String busiTitle;
	private String busiType;
	private String busiArea;
	private String busiStyle;
}
