package com.eventi.left.punish.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PunishVO {
	private String punNo; //제재요청번호
	private String userId; //회원ID
	private String targetId; //대상ID
	private String targetCat; //대상구분
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dt; //신고일
	private String banType; //신고종류
	private String banCntn; //신고사유
	private String banRslt; //처리결과
	private String writer; //신고 대상 글 작성자
	private int stOrder; //정렬
	
	Integer first = 1; //첫페이지
	Integer last = 5; //마지막페이지
}
