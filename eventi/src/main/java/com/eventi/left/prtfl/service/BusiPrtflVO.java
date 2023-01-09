package com.eventi.left.prtfl.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusiPrtflVO {
	private String userId; //회원ID
	private String intro; //업체소개
	private Integer tel; //전화번호
	private Date dt; //등록시간
	private String img; //대표이미지
}
