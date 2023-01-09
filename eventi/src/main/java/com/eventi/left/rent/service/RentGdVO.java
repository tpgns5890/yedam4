package com.eventi.left.rent.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class RentGdVO {
	private String goodsNo; //물품번호
	private String userId; // 회원ID
	private String goodsNm; //물품이름
	private String stock; //재고
	private Integer totalStock; //총수량
	private Integer rentalPrc; //대여가격
	private String rpstImg; //대표이미지
	private String saleYn; //판매중지여부
	private String goodsSize; //물품크기
}
