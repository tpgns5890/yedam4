package com.eventi.left.likes.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class LikesVO { 
	private String likeNo;		//좋아요번호
	private String userId;		//작성자Id
	private String targetNo;	//대상번호
	private String category;	//대상구분
}