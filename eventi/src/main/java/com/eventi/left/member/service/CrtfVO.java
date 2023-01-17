package com.eventi.left.member.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrtfVO {
	private int certId;
	private String userId;
	private String certName;
	private String certNum;
	private String certType;
	private String accpYN;
}
