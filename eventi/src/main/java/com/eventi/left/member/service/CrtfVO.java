package com.eventi.left.member.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrtfVO {
	private String crtfId;
	private String userId;
	private String crtfName;
	private String crtfNum;
	private String accpYN;
	private String crtfType;
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date accpDate;
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date reqDate;
	
	Integer first;
	Integer last;
}
