package com.eventi.left.prtfl.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class McPrtflVO {
	private String userId; //회원ID
	private String oneIntro; //한줄소개
	private Integer carrYear; //경력
	private Integer castingPrice; //섭외비
	private String mcArea; //사회가능지역
	private String mcType; //세부분야
	private String progStyle; //진행스타일
	private String selfIntro; //자기소개
	private String career; //경력사항
	private Integer inq; //조회수
	private Date dt; //작성시간
	private String img; //대표이미지
}
