package com.eventi.left.estimate.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class PropGdVO {
	private String pno;
	private String goodsNo;
	private String goodsNm;
	private Integer unitPrc;
	private Integer count;
}
