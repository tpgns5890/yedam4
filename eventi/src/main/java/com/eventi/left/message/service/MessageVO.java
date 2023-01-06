package com.eventi.left.message.service;

import java.util.Date;

import com.eventi.left.bboard.service.BboardVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageVO {
	private String msgId; //채팅ID
	private String chaId; //채팅방ID
	private String toId; //받는사람ID
	private String fromId; //보낸사람ID
	private String msgCntn; //채팅내용
	private Date sendDt; //보낸시간
	private String readYn; //읽은여부
}
