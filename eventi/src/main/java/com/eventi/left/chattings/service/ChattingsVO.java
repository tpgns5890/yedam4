package com.eventi.left.chattings.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChattingsVO {
	private String cId; //채팅방ID
	private String toId; //받는사람ID
	private String fromId; //보내는사람ID
}
