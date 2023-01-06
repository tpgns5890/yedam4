package com.eventi.left.chattings.service;

import java.util.Date;

import com.eventi.left.bboard.service.BboardVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class chattingsVO {
	private String cId; //채팅방ID
	private String toId; //받는사람ID
	private String fromId; //보내는사람ID
}
