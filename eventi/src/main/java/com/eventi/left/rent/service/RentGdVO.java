package com.eventi.left.rent.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class RentGdVO {
	private String goodsNo;
	private String userId;
	private String goodsNm;
	private String stock;
	private Integer totalStock;
	private Integer rentalPrc;
	private String rpstImg;
	private String saleYn;
	private String goodsSize;
}
