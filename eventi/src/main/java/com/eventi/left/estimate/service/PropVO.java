package com.eventi.left.estimate.service;

import java.sql.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class PropVO {
	private String pno; //제안서 번호
	private String eno; //견적요청서 번호
	private String userId; //회원ID
	private String companyInt; //업체소개
	private Integer eventExps; //행사진행비
	private Integer totalPrc; //총금액
	private String adoptionYn; //채택유무
	private Date writingDt; //작성일시
	
	private List<PropGdVO> propGdList;
}
