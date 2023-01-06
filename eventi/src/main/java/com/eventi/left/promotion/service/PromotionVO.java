package com.eventi.left.promotion.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PromotionVO {
	private String proNo; //홍보게시글번호
	private	String userId; //회원ID
	private String eName; //행사명
	private Date ePeriod; //행사기간
	private String loc; //위치
	private String intro; //소개
	private String eType; //행사유형
	private String entrance; //입장료유무
	private String accommodate; //수용인원
	private String info; //주최측정보
}
