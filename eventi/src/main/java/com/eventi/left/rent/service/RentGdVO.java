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
	private Integer TotalStock;
	private Integer RentalPrc;
	private String RpstImg;
	private String slaeYn;
	private String goodsSize;
}
