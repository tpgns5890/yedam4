package com.eventi.left.casting.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CastingVO {
	private String castingNo; //섭외번호
	private String userId; //사회자ID
	private String clientId; //의뢰자ID
	private String eType; //행사분야
	private String eIntro; //행사소개
	private Date eSdt; //행사시작날짜
	private Date eLdt; //행사마지막날짜
	private String eTime; //행사시간
	private Integer castingPrice; //섭외비
	private Date dt; //작성일자
}
