package com.eventi.left.rent.service;

import java.sql.Date;

public class RentListVO {
	private String rlstNO; //대여내역번호
	private Integer qty; //수량
	private Date eDt; // 행사날짜 
	private String stllNo; //결제번호
	private String goodsNo; //물품번호
	private String returnYn; //반납여부
}
