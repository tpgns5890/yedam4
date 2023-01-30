package com.eventi.left.estimate.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EstVO {
	private String eno; //견적요청서 번호
	private String userId; // 회원ID	
	private String eventType; // 행사유형
	private String pats;  // 행사규모
	private String expectedPl; // 행사예정장소
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date eventDate; // 행사예정일
	private String eventTime; //행사시작시간
	private String eventDuration; //행사소요시간
	private String expectedLocal; //행사예상지역
	private String wishes; //행사희망사항
	private Date writingDate; //작성일시
	private String adoptionYn; //제안서 채택유무
	
	//private PropVO propVO;  //PropVO Join 컬럼
	
	Integer first; //첫 페이지
	Integer last; //마지막 페이지
}
