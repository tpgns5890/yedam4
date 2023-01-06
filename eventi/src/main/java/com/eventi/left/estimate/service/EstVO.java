package com.eventi.left.estimate.service;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class EstVO {
	private String eno; //견적요청서 번호
	private String userId; // 회원ID
	private String eventType; // 행사유형
	private String pats;  // 행사규모
	private String expectedpl; // 행사예정장소
	private Date eventDate; // 행사예정일
	private String eventTime; 
	private String eventDuration;
	private String expectedLocal;
	private String wishes;
	private Date writingDate;
	private String adoptionYn;
}
