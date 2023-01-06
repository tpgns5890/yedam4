package com.eventi.left.estimate.service;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class PropVO {
	private String pno;
	private String eno;
	private String userId;
	private String companyInt;
	private Integer eventExps;
	private Integer totalPrc;
	private String adoptionYn;
	private Date writingDt;
}
