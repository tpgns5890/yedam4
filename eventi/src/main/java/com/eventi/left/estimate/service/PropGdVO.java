package com.eventi.left.estimate.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class PropGdVO {
	private String pno; //제안서 번호
	private String goodsNo; //물품 번호
	private String goodsNm; //물품이름
	private Integer unitPrc; //단가
	private Integer count; //수량
}
