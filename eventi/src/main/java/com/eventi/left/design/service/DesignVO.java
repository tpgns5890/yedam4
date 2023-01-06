package com.eventi.left.design.service;

import java.util.Date;

import com.eventi.left.bboard.service.BboardVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DesignVO {
	private String dgnNo; //디자인번호
	private String userId; //회원ID
	private String cNo; //공모전번호
	private String name; //디자인이름
	private String caregory; //디자인카테고리
	private String cntn; // 디자인소개
	private String winYn; //우승여부
	private Date dt; //등록시간
	private String centerImg; //대표이미지
	private String pubcYn; //공개여부
}
