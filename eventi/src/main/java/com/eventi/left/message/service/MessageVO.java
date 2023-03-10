package com.eventi.left.message.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(pattern = "hh:mm")
	private Date sendDt; //보낸시간
	private String readYn; //읽은여부
}
