package com.eventi.left.casting.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CastingVO {
	private String castingNo;     //섭외번호
	private String userId;        //사회자ID
	private String clientId;      //의뢰자ID
	private String eType;         //행사분야
	private String eIntro;        //행사소개
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eSdt;            //행사시작날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eLdt;            //행사마지막날짜
	private String eStime;        //행사시작시간
	private String eLtime;        //행사끝나는시간
	private Integer castingPrice; //섭외비
	private Date dt;              //작성일자
	private String progress;      //진행상태
	private String cancel;        //취소사유
}
