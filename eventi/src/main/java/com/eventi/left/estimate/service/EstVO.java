package com.eventi.left.estimate.service;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class EstVO {
	private String eno;
	private String userId;
	private String eventType;
	private String scale;
	private String expectedpl;
	private Date eventDate;
	private String eventTime;
	private String eventDuration;
	private String expectedLocal;
	private String wishes;
	private Date writingDate;
}
